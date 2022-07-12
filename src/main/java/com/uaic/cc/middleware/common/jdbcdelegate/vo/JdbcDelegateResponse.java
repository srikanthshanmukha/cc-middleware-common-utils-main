package com.uaic.cc.middleware.common.jdbcdelegate.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JdbcDelegateResponse {
	
	protected String messageId;
	protected boolean isSuccessful;
	protected String message;
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	protected LocalDateTime requestTimestamp;
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	protected LocalDateTime reponseTimestamp;

	public JdbcDelegateResponse() {
		super();
	}


	public JdbcDelegateResponse(String messageId, boolean isSuccessful, String message, LocalDateTime requestTimestamp,
			LocalDateTime reponseTimestamp) {
		super();
		this.messageId = messageId;
		this.isSuccessful = isSuccessful;
		this.message = message;
		this.requestTimestamp = requestTimestamp;
		this.reponseTimestamp = reponseTimestamp;
	}


	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getRequestTimestamp() {
		return requestTimestamp;
	}


	public void setRequestTimestamp(LocalDateTime requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}


	public LocalDateTime getReponseTimestamp() {
		return reponseTimestamp;
	}


	public void setReponseTimestamp(LocalDateTime reponseTimestamp) {
		this.reponseTimestamp = reponseTimestamp;
	}
	
	

}
