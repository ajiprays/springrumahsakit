package com.aji.rumahsakit.rs.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse
{
	@JsonProperty(value="message")
	public String message;

	public MessageResponse(String message) 
	{
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
