package com.uaic.cc.middleware.common.utils.credentials.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;

public class Credentials {

	protected Collection<Credential> credentials;
	
	public Credentials() {
		super();
	}

	public Collection<Credential> getCredentials() {
		if(credentials == null) credentials = new ArrayList<Credential>();
		return credentials;
	}

	public void setCredentials(Collection<Credential> credentials) {
		this.credentials = credentials;
	}
	
	@JsonIgnore
	public String toJson() throws JsonProcessingException {
		return JsonObjectMapper.getInstance().writeValueAsString(this);
	}

	@JsonIgnore
	public static Credentials fromJson(String json) throws JsonMappingException, JsonProcessingException {
		if(json == null || json.trim().isBlank()) return null;
		return JsonObjectMapper.getInstance().readValue(json, Credentials.class);
	}
}
