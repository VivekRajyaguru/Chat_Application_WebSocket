package com.example.demo.model;

public class Chat {

	private String userName;
	private String comments;
	
	public Chat() {}
	
	public Chat(String userName, String comments) {
		this.userName = userName;
		this.comments = comments;		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
