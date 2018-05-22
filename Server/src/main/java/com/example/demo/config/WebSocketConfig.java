package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	// Creating EndPoints for WebSocket can add multiple end points also
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndPoint) {
		stompEndPoint.addEndpoint("/websocket-example").setAllowedOrigins("*").withSockJS();		
	}

	
	// Override Default Message Broker
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry  
        .setApplicationDestinationPrefixes("/app")  // prefix for Websocket
        .enableSimpleBroker("/topic"); // use to push the message
    }
	
	
}
