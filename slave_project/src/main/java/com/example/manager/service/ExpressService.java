package com.example.manager.service;

import com.example.manager.event.OrderMessageEvent;
import com.rabbitmq.client.Channel;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Created by majf
 * 2019/1/12 15:47
 */
@Component
public class ExpressService  {

    //RabbitMq发createOrderQueue队列的消息过来时，Spring自动调用该方法
    @RabbitListener(queues = "createOrderQueue")
    public void orderConsumer(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception{
       try {
           JSONObject jsonObject = JSONObject.fromObject(message);
//           OrderMessageEvent event = (OrderMessageEvent)JSONObject.toBean(jsonObject, OrderMessageEvent.class);
           System.out.println("----------收到MQ发来的消息--------" + jsonObject.getString("orderId"));
           System.out.println("----------收到MQ发来的消息--------" + message );
           System.out.println("----------收到MQ发来的消息--------" + jsonObject.toString() );
           //TODO 业务代码：主键重复判断：实现消息消费幂等性，重复则抛异常
           //ack确认 告诉MQ消息已消费
           //MQ收到后会删除Queue中该条消息
           channel.basicAck(tag, false);//如果到这一步网络断了，网络通后，MQ会重新发消息
       }catch (Exception e){
           //MQ收到后会重发Queue中该条消息
           //记录并控制重发次数，防止死循环，------->redis记录重发次数
           //重发超次，通知人工处理
           System.out.println("----------无法解析MQ发来的消息--------" + message);
           channel.basicNack(tag, false, false);
       }

    }
}
