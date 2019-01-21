package com.myboot.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 订单创建短信事件
 * Created by majf
 * 2019/1/2 14:57
 */
public class OrderMessageEvent extends ApplicationEvent implements Serializable{

    private static final long serialVersionUID = 1L;

    private String orderId;

    public OrderMessageEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    @Override
    public String toString() {
        return "Event{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
