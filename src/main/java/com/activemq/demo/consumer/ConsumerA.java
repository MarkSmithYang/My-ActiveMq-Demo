package com.activemq.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * @author biaoyang
 * @Description:
 * @date 2018/8/30 003015:42
 */
@Service
public class ConsumerA {

    //同时(监听)消费生产消费者模式的消息和发布订阅模式的消息
    @JmsListeners({@JmsListener(destination = "queue"), @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")})
//    @JmsListeners({@JmsListener(destination = "queue",containerFactory="jmsListenerContainerQueue"),@JmsListener(destination = "topic",containerFactory = "jmsListenerContainerTopic")})
    //queue消息可以不用指定containerFactory=""
    @SendTo("consumer")//把此方法返回的数据发送到(监听)消费("consumer")那里去,实际测试的是,只有生产消费者模式才能发送出去
    public String receiveMsg(String msg) {
        System.out.println("<<<=======消费者AAAAA收到消息:" + msg);
        return "<<<我是被转发的消息哦>>>" + msg;
    }
}
