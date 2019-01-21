package com.myboot.service.Imp;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现集群部署场景下的分布式锁：利用zookeeper临时顺序节点的唯一性和临时性，顺序性实现
 * 自己实现分布式锁，该类实现Lock接口，使得该类的使用方式和java内部Lock保持一致
 * Created by majf
 * 2018/12/25 12:03
 */
public class ZKDistributeLock implements Lock {

    //临时节点路径：当作分布式锁目录
    private String orderLockPath;

    //临时顺序节点路径：当作分布式锁排队号
    private String currentPath;

    //前一个临时顺序节点路径：当作分布式锁排在哪个节点后面
    private String previousPath;

    //zookeeper客户端
    private ZkClient zkClient;


    //构造器完成准备工作：保存指定的临时节点路径，创建zookeeper客户端，设置序列化器
    public ZKDistributeLock(String serverString, String orderLockPath) {
        super();
        this.orderLockPath = orderLockPath;
        zkClient = new ZkClient(serverString);
        zkClient.setZkSerializer(new MyZkSerializer());
        //如果父节点不存在，先创建：当作所有分布式锁排队的主目录
        if(!zkClient.exists(orderLockPath)){
            try {
                zkClient.createPersistent(orderLockPath);
            }catch (ZkNodeExistsException e){

            }
        }
    }

    @Override
    public boolean tryLock() {
        //已经排队了就不创建节点了
        if(currentPath == null){
            //创建指定的临时顺序节点路径
            currentPath = zkClient.createEphemeralSequential(orderLockPath + "/", "order_lock");
        }
        //获取所有排队中的锁
        List<String> children = zkClient.getChildren(orderLockPath);
        //对锁排序
        Collections.sort(children);
        if(currentPath.equals(orderLockPath + "/" + children.get(0))){
            //如果是第一个，说明本线程可执行了
            return true;
        }else {
            //如果不是第一个，说明还要继续等待，获取前一个锁路径，后续监听该锁删除操作
            int currentPathIndex = children.indexOf(currentPath.substring(orderLockPath.length() + 1));
            previousPath = orderLockPath + "/" + children.get(currentPathIndex - 1);
            return false;
        }
    }

    @Override
    public void unlock() {
        //删除当前锁
        zkClient.delete(currentPath);
    }

    @Override
    public void lock() {
        if(!tryLock()){
            //没有获得锁：注册节点的watcher监听
            waitForLock();
            //被唤醒后，循环调用自己，重新去获取锁
            lock();
        }
    }

    /**
     * 注册节点的监听
     */
    public void waitForLock(){
        //并发工具：计数器设置值为1需要等待，减到0时参与竞争锁
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //创建监听器
        IZkDataListener iZkDataListener = new IZkDataListener() {
            //实现该方法：监听指定节点路径是否被删除，删除会调用该方法
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("----------节点删除了----------");
                //计数器减1：唤醒线程参与竞争锁
                countDownLatch.countDown();
            }

            //实现IZkDataListener接口必须生成的方法，因为没有用到可以不用写具体的实现内容
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }
        };

        //注册zookeeper节点数据监听:监听前一个锁什么时候被删除
        zkClient.subscribeDataChanges(previousPath, iZkDataListener);
        //如果前一个锁还存在：阻塞线程
        if(zkClient.exists(previousPath)){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //当监听到前一个锁删除后，唤醒线程并取消zookeeper节点数据监听
        zkClient.unsubscribeDataChanges(previousPath, iZkDataListener);
    }









    //后面几个实现Lock接口必须生成的方法，因为没有用到可以不用写具体的实现内容
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
