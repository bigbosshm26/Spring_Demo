package com.example.demo.exception;

import java.util.List;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<Object> messages;
	
	public CustomException(List<Object> messages) {
		super();
		this.messages = messages;
	}
	
	public List<Object> getMessages() {
		return messages;
	}

	public void setMessages(List<Object> messages) {
		this.messages = messages;
	}
}
