package com.myboot.listener;

import com.myboot.event.OrderMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 监听订单创建长大成人短信事件，然后发送短信通知
 * Created by majf
 * 2019/1/2 15:05
 */
@Service
public class OrderWeixinListener implements ApplicationListener<OrderMessageEvent> {

    /**
     * 当事件发生时调用
     */
    @Override
    public void onApplicationEvent(OrderMessageEvent applicationEvent) {
        System.out.println("----------发送微信通知--------" + applicationEvent.getOrderId());
    }

}
