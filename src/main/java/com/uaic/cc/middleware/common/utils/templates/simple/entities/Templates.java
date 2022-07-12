package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;

public class Templates {

	protected Collection<Template> templates;

	public Templates() {
		super();
	}

	public Collection<Template> getTemplates() {
		if (templates == null) templates = new ArrayList<Template>();
		return templates;
	}

	public void setTemplates(Collection<Template> templates) {
		this.templates = templates;
	}
	
	@JsonIgnore
	public String toJson() throws JsonProcessingException {
		return JsonObjectMapper.getInstance().writeValueAsString(this);
	}

	@JsonIgnore
	public static Templates fromJson(String json) throws JsonMappingException, JsonProcessingException {
		if(json == null || json.trim().isBlank()) return null;
		return JsonObjectMapper.getInstance().readValue(json, Templates.class);
	}

	@Override
	public boolean equals(Object obj) {
		return getTemplates().equals(((Templates)obj).getTemplates());
	}
	
	
}
