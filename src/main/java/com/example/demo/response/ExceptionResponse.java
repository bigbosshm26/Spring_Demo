package com.example.demo.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExceptionResponse {

	private final String occurTime;
	private int statusCode;
	private final String exceptionName;
	private final List<String> details;
	private final String path;

	public ExceptionResponse(int statusCode, String exceptionName, List<String> details, String path) {
		super();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.occurTime = dateFormat.format(new Date());
		this.statusCode = statusCode;
		this.exceptionName = exceptionName;
		this.details = details;
		this.path = path;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public List<String> getDetails() {
		return details;
	}

	public String getPath() {
		return path;
	}
	
}