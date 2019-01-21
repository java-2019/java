package com.myboot.service.Imp;

import com.myboot.event.OrderMessageEvent;
import com.myboot.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;

/**
 * Created by majf
 * 2018/12/25 10:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    private static int i = 1;

    @Value("${zookeeper.server}")
    private String ZK_SERVER;

    @PostConstruct
    public void init() {
        //redis的list结构实现令牌桶效果，初始化令牌
        //正式环境使用异步调试初始化：如定时初始化
        for (int i= 0; i<10; i++){
            stringRedisTemplate.opsForList().rightPush("token_lists", String.valueOf(i));
        }
        System.out.println("令牌桶初始化成功");
    }

    /**
     * 需要先启动redis和zookeeper
     */
    public Boolean rushToBuy(String goodsCode, final String userId) throws Exception {
//        限制用户访问频率：10秒钟内只能访问一次
//        redis创建key,EX有效期为10秒，NX不存在时创建
       Boolean value =  stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //使用组合命令set(key,value,有效期,不存在时才创建标记)
                Boolean isSuccessful = redisConnection.set(userId.getBytes(), goodsCode.getBytes(), Expiration.milliseconds(10000L), RedisStringCommands.SetOption.SET_IF_ABSENT);
                return isSuccessful;
            }
        });

        //redis创建key成功继续访问，创建不成功不让访问
       if(!value){
            System.out.println("你的操作频率太高了 ： " + userId);
            return false;
       }

        //令牌桶算法：init()初始化已经在redis生成了10个令牌，在这里取令牌
        // redis访问速度比较快，没拿到则抢购失败，不用继续后面的业务流程
        String token = stringRedisTemplate.opsForList().leftPop("token_lists");
       if(token == null || "".equals(token)){
           System.out.println("没有抢到token，抢购失败！ ： " + userId);
           return false;
       }

        //TODO 判断userId是否存在

        //最后再执行业务操作
        String orderId = createOrder();

        //发布一个订单创建事件：spring事件发布订阅机制(同步调用，循环监听，异步执行)
        //事件监听机制发送短信、微信通知、RabbitMQ消息
        applicationContext.publishEvent(new OrderMessageEvent(this, orderId));
        return true;
    }

    /**
     * 需要先启动zookeeper
     */
    @Override
    public String createOrder() {
        //java内部锁ReentrantLock，无法满足集群分布式系统结构下生成订单号唯一因为每台服务器都会生成一把锁
//        Lock lock = new ReentrantLock();
        //实现分布式锁:利用zookeeper临时顺序节点的唯一性只要线程能创建指定节点则获得锁，生成订单编号后释放锁
        //满足集群分布式系统结构下生成订单号唯一
        //临时顺序节点：如果线程挂了，zookeeper会自动删除临时节点，防止死锁
        Lock lock = new ZKDistributeLock(ZK_SERVER, "/order_lock_list");
        try {
            lock.lock();
            String orderId = createOrderId();
            return orderId;
        }finally {
            lock.unlock();
        }
        // TODO 实现分布式锁保证订单号唯一性，获得锁后可实现具体订单业务代码
    }


    public String createOrderId() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        String orderId = format.format(now) + i++;
        System.out.println("createOrder , orderId = " + orderId);
        return orderId;
    }

}
