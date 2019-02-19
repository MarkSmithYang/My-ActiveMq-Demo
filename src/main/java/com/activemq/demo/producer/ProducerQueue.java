package com.activemq.demo.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author biaoyang
 * @Description:
 * @date 2018/8/30 003015:30
 */
@Service
public class ProducerQueue {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMsg(String destinationName, String messages) {
        //当然了可以启动服务时就实例化好,然后直接注入使用,我这里就不去注入了直接在这里实例化,不用主应用类里实例化好的了
        System.err.println("================>>>>生产消费者模式:" + messages);
        jmsTemplate.convertAndSend(new ActiveMQQueue(destinationName), messages);
    }
}
