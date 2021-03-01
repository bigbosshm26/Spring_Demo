package com.example.demo.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final BindingResult bindingResult;

	public BindingResult getBindingResult(){
		return bindingResult;
	}

	public ValidationException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
}
