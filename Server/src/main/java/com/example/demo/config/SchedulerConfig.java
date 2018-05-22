package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.model.UserResponse;

@EnableScheduling
@Configuration
public class SchedulerConfig {
	
	@Autowired
    SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 300000)
    public void sendAdhocMessages() {
        template.convertAndSend("/topic/user", new UserResponse("Fixed Delay Scheduler"));
    }
}
