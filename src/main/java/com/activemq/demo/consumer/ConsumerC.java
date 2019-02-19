package com.activemq.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Service;

/**
 * @author biaoyang
 * @Description:
 * @date 2018/8/30 003015:42
 */
@Service
public class ConsumerC {

    //同时(监听)消费生产消费者模式的消息和发布订阅模式的消息
    @JmsListeners({@JmsListener(destination = "queue"), @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")})
    public void receiveMsg(String msg) {
        System.out.println("<<<=======消费者CCCCC收到消息:" + msg);
    }
}
