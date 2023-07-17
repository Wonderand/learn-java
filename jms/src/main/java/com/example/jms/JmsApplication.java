package com.example.jms;

import com.example.jms.pojo.Email;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class JmsApplication {

    @Bean
    public JmsListenerContainerFactory<?> myFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // 这将为该工厂提供所有自动配置的默认值，包括报文转换器
        configurer.configure(factory, connectionFactory);
        // 如有必要，您还可以覆盖某些设置
        return factory;
    }

    @Bean // 使用TextMessage将消息内容序列化为json格式
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) {
        // 启动应用程序
        ConfigurableApplicationContext context = SpringApplication.run(JmsApplication.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // 用POJO发送信息--模板重用信息转换器
        System.out.println("发送电子邮件.");
        jmsTemplate.convertAndSend("mailbox", new Email("2290641663@qq.com", "Hello"));
    }

}