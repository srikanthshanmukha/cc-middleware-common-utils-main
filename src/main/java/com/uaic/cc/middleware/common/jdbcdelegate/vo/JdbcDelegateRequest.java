package com.uaic.cc.middleware.common.jdbcdelegate.vo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JdbcDelegateRequest {

	protected String templateId;
	protected String messageId;
	protected String claimNumber;
	protected String username;
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	protected LocalDateTime timestamp;
	protected Map<String, String> templateParameters;
	
	public JdbcDelegateRequest() {
		super();
	}

	public JdbcDelegateRequest(String templateId, String messageId, String claimNumber, String username,
			LocalDateTime timestamp, Map<String, String> templateParameters) {
		super();
		this.templateId = templateId;
		this.messageId = messageId;
		this.claimNumber = claimNumber;
		this.username = username;
		this.timestamp = timestamp;
		this.templateParameters = templateParameters;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, String> getTemplateParameters() {
		if(templateParameters == null) templateParameters = new HashMap<String, String>();
		return templateParameters;
	}

	public void setTemplateParameters(Map<String, String> templateParameters) {
		this.templateParameters = templateParameters;
	}
	
	
	
}
