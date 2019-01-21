package com.example.manager.jms;

import com.example.manager.common.constant.MQConstants;
import com.example.manager.common.utils.JsonMapper;
import com.example.manager.event.OrderMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by majf
 * 2019/1/15 15:15
 */
@Component
@Slf4j
public class ComsumerFactory implements DisposableBean, InitializingBean {
    @Value("${spa.rocketmq.server}")
    private String server;
    @Value("${spa.rocketmq.group.jobResult}")
    private String group;

    private DefaultMQPushConsumer jobResultConsumer;

    private void initConsumer() throws MQClientException {
        log.info("init consumer start");
        //同一group下的consumer需要订阅相同的topic
        jobResultConsumer = new DefaultMQPushConsumer(group);
        jobResultConsumer.setConsumeMessageBatchMaxSize(1);
        jobResultConsumer.setNamesrvAddr(server);
        jobResultConsumer.setMessageModel(MessageModel.CLUSTERING);

        //订阅PushTopic下Tag为push的消息
        String jobNotifyTags = MQConstants.TAG_ORDER;
        jobResultConsumer.subscribe(MQConstants.TOPIC_CREATE_ORDER, jobNotifyTags);
        //程序第一次启动从消息队列头取数据
        jobResultConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        jobResultConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list){
                    String body =  new String(msg.getBody());
                    OrderMessageEvent event = JsonMapper.simpleDateMapper().fromJson(body, OrderMessageEvent.class);
                    System.out.println("----------收到RocketMQ发来的消息--------" + event.getOrderId());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
//        jobResultConsumer.registerMessageListener(
//                (MessageListenerConcurrently) (list, Context) -> {
//                    for (MessageExt msg : list) {
//                        boolean status = MessageListenerFactory.onMessage(msg);
//                        if(!status){
//                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                        }
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//        );
        jobResultConsumer.start();
        log.info("init consumer end");
    }

    @Override
    public void destroy() throws Exception {
        log.info("destroy consumer start");
        if(jobResultConsumer != null){
            jobResultConsumer.shutdown();
        }
        log.info("destroy consumer end");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initConsumer();
    }
}
