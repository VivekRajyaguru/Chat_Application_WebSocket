package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;

import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.model.UserResponse;


@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	SimpMessageSendingOperations operation;
	
	@MessageMapping("/user")
	@SendTo("/topic/user")
	public UserResponse getMessage(User user) {
		
		return new UserResponse("Hi " + user.getName());
	}
	
	@MessageMapping("/addUser")
	@SendTo("/topic/showMessage")
	public UserResponse addUser(User user) {
		
		return new UserResponse("New User " + user.getName() + " is Added");
	}
	
	
	@MessageMapping("/chatComments")
	//@SendTo("/topic/showComments")
	@SendToUser("/topi")
	public void comments(Chat chat) {
		
		/*template.convertAndSend("/topic/showComments", new UserResponse(chat.getComments()));*/
		operation.convertAndSend("/topic/showComments", new UserResponse(chat.getComments()), new MessagePostProcessor() {
			
			@Override
			public Message<?> postProcessMessage(Message message) {
				System.out.println("Chat Message ---- " + message);
				new UserResponse("Edited");
				return message;
			}
		});
	}
	
	
}
