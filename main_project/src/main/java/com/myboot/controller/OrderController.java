package com.myboot.controller;

import com.myboot.remote.api.OrderService;
import com.myboot.service.Imp.OrderServiceImpl;
import com.myboot.service.ThreadDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by majf
 * 2018/12/22 17:22
 */
@RestController
@RequestMapping(value = "/thread")
public class OrderController {

    @Autowired
    private ThreadDemo threadDemo;

    @Autowired
    private OrderService orderService;

    /**
     * 模拟现时抢购：redis实现限制用户点击频率
     */
    @RequestMapping(value = "/rush/buy")
    public void rushToBuy(int clientCount, String goodsCode, String userId){
        //循环屏障：设置多个请求同时到达后同时并发操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(clientCount);
        for (int i = 1; i<= clientCount; i++){
            String user = String.valueOf(i)+userId;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("----------准备----------" + Thread.currentThread().getName());
                    try {
                        //循环屏障：等待多个请求同时到达
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    try {
                        orderService.rushToBuy(goodsCode, user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    /**
     * 模拟高并发下单：zookeeper实现分布式锁
     * @param clientCount:并发数
     */
    @RequestMapping(value = "/create/order")
    public void createOrder(int clientCount){
        //循环屏障：设置多个请求同时到达后同时并发操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(clientCount);
        for (int i = 1; i<= clientCount; i++){
            new Thread(new Runnable() {
                //创建订单实例放到线程里，模拟集群环境创建多个实例（单服务器默认都是单例）
                OrderService order = new OrderServiceImpl();
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "----------准备----------");
                    try {
                        //循环屏障：等待多个请求同时到达
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
//                    orderService.createOrder();
                    //循环屏障：多个请求到达后再并发创建订单
                    order.createOrder();
                }
            }).start();
        }

    }

    /**
     *
     * @param time 最外层循环次数
     */
    @RequestMapping(value = "/demo")
    public void threadDemo(int time){
        threadDemo.ThreadDemo(time);
        System.out.println("Demo：" + threadDemo.hashCode());
    }
}
