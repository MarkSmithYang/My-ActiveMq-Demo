package com.activemq.demo;

import com.activemq.demo.producer.ProducerQueue;
import com.activemq.demo.producer.ProducerTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ProducerTopic producerTopic;
    @Autowired
    private ProducerQueue producerQueue;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 11; i++) {
            //发布生产消费者消息
            producerQueue.sendMsg("queue", "我是生产消费者消息:" + i);
            //发布订阅消息
            producerTopic.sendMsg("topic", "我是发布订阅者消息:" + i);
        }

    }

}
