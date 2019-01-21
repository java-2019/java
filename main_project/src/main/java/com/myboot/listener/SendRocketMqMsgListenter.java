package com.myboot.listener;

import com.myboot.common.utils.JsonMapper;
import com.myboot.event.OrderMessageEvent;
import com.myboot.jms.PushMessageRocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by majf
 * 2019/1/15 11:56
 */
@Service
public class SendRocketMqMsgListenter implements ApplicationListener<OrderMessageEvent> {

    @Autowired
    private PushMessageRocketMqProducer pushMessageRocketMqProducer;
    /**
     * 当事件发生时调用
     */
    @Override
    public void onApplicationEvent(OrderMessageEvent applicationEvent) {
        System.out.println("----------准备发送RocketMq消息--------" + applicationEvent.getOrderId());
        try {
            sendMsg(applicationEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息到MQ
     */
    public void sendMsg(OrderMessageEvent event) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("orderId", event.getOrderId());
        pushMessageRocketMqProducer.sendMsg(event.getOrderId(), JsonMapper.simpleDateMapper().toJson(data));
    }
}
