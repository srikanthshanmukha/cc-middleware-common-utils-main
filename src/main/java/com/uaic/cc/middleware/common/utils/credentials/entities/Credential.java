package com.uaic.cc.middleware.common.utils.credentials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;

public class Credential {

	protected String id;
	protected String username;
	// TODO: change password to char
	protected String password;

	public Credential() {
		super();
	}

	public Credential(String id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public String toJson() throws JsonProcessingException {
		return JsonObjectMapper.getInstance().writeValueAsString(this);
	}

	@JsonIgnore
	public static Credential fromJson(String json) throws JsonMappingException, JsonProcessingException {
		if(json == null || json.trim().isBlank()) return null;
		return JsonObjectMapper.getInstance().readValue(json, Credential.class);
	}

}
