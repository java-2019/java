package com.myboot.service.Imp;

import com.myboot.service.ThreadDemoBusiness;
import org.springframework.stereotype.Service;

/**
 * 通过参数subStatDo判断同步方法mainStat和同步方法subStat轮流执行
 * Created by majf
 * 2018/12/22 15:54
 */
@Service
public class ThreadDemoBusinessImpl implements ThreadDemoBusiness {

    //设置一个参数用来协调同步方法该哪个执行：默认true：subStat先执行
    private boolean subStatDo = true;

    @Override
    public synchronized void mainStat(int time) {
        //判断应该subStat执行时，阻塞自己
        if(subStatDo){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //唤醒后执行的方法体
        for (int i=1; i<=5; i++){
            System.out.println(Thread.currentThread().getName() + "：time = " + time + ", mainStat： " + i);
        }
        //执行完修改参数：轮到subStat执行了
        subStatDo = true;
        //唤醒正在等待的线程参与竞争
        this.notify();
    }

    @Override
    public synchronized void subStat(final int time) {
        //判断应该mainStat执行时，阻塞自己
        if(!subStatDo){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //唤醒后执行的方法体
        for (int i=1; i<=10; i++){
            System.out.println(Thread.currentThread().getName() + "：time = " + time + ", subStat： " + i);
        }
        //执行完修改参数：轮到mainStat执行了
        subStatDo = false;
        //唤醒正在等待的线程参与竞争
        this.notify();
    }
}
