package com.example.manager.event;


/**
 * 订单创建短信事件
 * Created by majf
 * 2019/1/2 14:57
 */
public class OrderMessageEvent{
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
