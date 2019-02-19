package com.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@EnableJms
public class ActiveMQApplication {

    /**
     * @Description:主应用类的主方法--启动方法
     * @author biaoyang
     * @date 2018/8/30 0030 15:22
     */
    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class, args);
    }

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String url;

    //实例化Queue和topic

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("topic");
    }


    //实例化对应的工厂,bean这个注入似乎是以方法名注入的,就是名称了,这就相当于使用xml的时候给bean的名称

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, url);
    }

    /**
     * @Description:生产消费者方式
     * @author biaoyang
     * @date 2018/8/30 0030 11:14
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /**
     * @Description:发布订阅方式
     * @author biaoyang
     * @date 2018/8/30 0030 11:14
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
