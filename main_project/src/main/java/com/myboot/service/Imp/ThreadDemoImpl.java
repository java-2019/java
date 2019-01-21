package com.myboot.service.Imp;

import com.myboot.service.ThreadDemo;
import com.myboot.service.ThreadDemoBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by majf
 * 2018/12/22 15:32
 */
@Service
public class ThreadDemoImpl implements ThreadDemo {

    @Autowired
    private  ThreadDemoBusiness threadDemoBusiness;

    /**
     * 循环调用两个同步方法：利用线程等待和唤醒机制便两个同步方法轮流执行
     * @param time:循环次数
     */
    @Override
    public void ThreadDemo(int time) {
        //创建一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //循环调用子方法
                for(int i = 1 ; i <= time; i++) {
                    threadDemoBusiness.subStat(i);
                }
            }
        }).start();

        //循环调用主方法
        for(int j = 1 ; j <= time; j++){
            threadDemoBusiness.mainStat(j);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //循环调用主方法
                for(int j = 1 ; j <= time; j++){
                    threadDemoBusiness.mainStat(j);
                }
            }
        }).start();
    }
}
