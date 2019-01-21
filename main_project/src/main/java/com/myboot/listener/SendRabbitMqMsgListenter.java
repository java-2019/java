package com.myboot.listener;

import com.myboot.event.OrderMessageEvent;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by majf
 * 2019/1/11 16:02
 */
@Service
public class SendRabbitMqMsgListenter implements SmartApplicationListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //rabbitMq消息发送完后回调该方法,ack代表是否发送成功
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //ack不为True 说明Mq还没已收到消息，不处理
                if(!ack){
                    return;
                }
                //ack为True 说明Mq已收到消息
                try {
                    //修改本地消息表消息发送记录的状态为“已发送”
                    System.out.println("----------MQ已收到消息--------" + correlationData.getId());
                    //TODO 业务代码
                }catch (Exception e){
                    System.out.println("MQ已收到消息后，修改本地消息表状态为出现异常");
                }
            }
        });
    }

    /**
     * 当事件发生时调用
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        OrderMessageEvent event = (OrderMessageEvent)applicationEvent;
        System.out.println("----------准备发送MQ消息--------" + event.getOrderId());

        //RabbitMQ异步方式处理订单如：下单成功后，转发订单信息到物流系统处理物流信息
        try {
            sendMsg(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当有多个监听器时，返回不同的值调试执行顺序
     * 返回值越小越排在前面
     */
    @Override
    public int getOrder() {
        return 2;
    }

    /**
     *监听哪个event
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == OrderMessageEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    /**
     * 发送消息到MQ
     */
    public void sendMsg(OrderMessageEvent event) throws Exception {
        JSONObject message = JSONObject.fromObject(event);
        rabbitTemplate.convertAndSend("creatOrderExchange","1",message.toString(), new CorrelationData(event.getOrderId()));
    }

    //TODO 调度任务，定时检查消息表，超时未发送则重发

}
