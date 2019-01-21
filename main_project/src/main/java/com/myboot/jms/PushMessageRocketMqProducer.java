package com.myboot.jms;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

import static com.myboot.common.constant.MQConstants.*;


/**
 * Created by majf
 * 2019/1/15 10:52
 */
@Service
@Lazy(false)
public class PushMessageRocketMqProducer{
    private DefaultMQProducer producer;
    @Value("${spa.rocketmq.server}")
    private String server;
    @Value("${spa.rocketmq.group.provider.manager}")
    private String group;

    private static final Logger log = LoggerFactory.getLogger(PushMessageRocketMqProducer.class);


    public boolean sendMsg(String id, String body){
        return send(id, TOPIC_CREATE_ORDER, TAG_ORDER, body);
    }

    public boolean send(String keys, String topic, String tag, String body) {
        try {
            log.info("send message topic={}, tag={}, body={}", topic, tag, body);
            Message msg = new Message(topic, tag, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //设置业务唯一id
            msg.setKeys(keys);
            //Call send message to deliver message to one of brokers.
            SendResult result = producer.send(msg);
            boolean ret = SendStatus.SEND_OK.equals(result.getSendStatus());
            if(!ret){
                log.error("send message fail: {}", result.toString());
            }else{
                log.info("send message suc: {}", result.toString());
            }
            return ret;
        } catch (UnsupportedEncodingException e) {
            log.error("send message error: topic={}, tag={}, keys={}, msg={}", topic, tag, keys, e.getMessage());
        } catch (InterruptedException e) {
            log.error("send message error: topic={}, tag={}, keys={}, msg={}", topic, tag, keys, e.getMessage());
        } catch (RemotingException e) {
            log.error("send message error: topic={}, tag={}, keys={}, msg={}", topic, tag, keys, e.getMessage());
        } catch (MQClientException e) {
            log.error("send message error: topic={}, tag={}, keys={}, msg={}", topic, tag, keys, e.getMessage());
        } catch (MQBrokerException e) {
            log.error("send message error: topic={}, tag={}, keys={}, msg={}", topic, tag, keys, e.getMessage());
        }
        return false;
    }

    @PreDestroy
    public void destroy() throws Exception {
        log.info("to destroy PushMessageRocketMqProducer");
        if(producer != null){
            producer.shutdown();
        }
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(server);
        producer.start();
        log.info("producer start successfully with group={}, server={}", "pushMessageGroup", server);
    }
}
