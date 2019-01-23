package com.example.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.myboot.remote.api.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by majf
 * 2019/1/22 11:10
 */
@RestController
@RequestMapping(value = "/thread")
public class DubboController {


    @Reference(check = false)
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
    }
