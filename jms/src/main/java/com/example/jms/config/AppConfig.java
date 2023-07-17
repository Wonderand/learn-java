package com.example.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

//@Configuration
//@EnableJms
//public class AppConfig {
//
//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setDestinationResolver(destinationResolver());
//		factory.setSessionTransacted(true);
//		factory.setConcurrency("3-10");
//		return factory;
//	}
//}