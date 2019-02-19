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
public class Consumer {

    //同时(监听)消费生产消费者模式的消息和发布订阅模式的消息
    @JmsListener(destination = "consumer")
    public void receiveMsg(String msg) {
        System.err.println(msg);
    }
}
