package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;

public class TemplateSection implements Comparable<TemplateSection> {

	protected int sortOrder;
	protected String stencil;
	protected Map<String, String> metadata;
	protected Collection<String> requiredParameters;

	public TemplateSection() {
		super();
		requiredParameters = new HashSet<String>();
		metadata = new HashMap<String, String>();
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getStencil() {
		if(stencil == null) stencil = "";
		return stencil;
	}

	public void setStencil(String stencil) {
		this.stencil = stencil;
	}
	
	public Map<String, String> getMetadata() {
		if(metadata == null) metadata = new HashMap<String,String>();
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Collection<String> getRequiredParameters() {
		if(requiredParameters == null) requiredParameters = new HashSet<String>();
		return requiredParameters;
	}

	public void setRequiredParameters(Collection<String> requiredParameters) {
		this.requiredParameters = requiredParameters;
	}

	@JsonIgnore
	public String toJson() throws JsonProcessingException {
		return JsonObjectMapper.getInstance().writeValueAsString(this);
	}

	@JsonIgnore
	public static TemplateSection fromJson(String json) throws JsonMappingException, JsonProcessingException {
		if(json == null || json.trim().isBlank()) return null;
		return JsonObjectMapper.getInstance().readValue(json, TemplateSection.class);
	}

	@Override
	public boolean equals(Object obj) {
		TemplateSection ts = (TemplateSection) obj;
		if(getSortOrder() != ts.getSortOrder()) return false;
		if(! getStencil().trim().equalsIgnoreCase(ts.getStencil().trim())) return false;
		if(!   (getRequiredParameters().size() == ts.getRequiredParameters().size() && 
				getRequiredParameters().containsAll(ts.getRequiredParameters()))) 
			return false;
		return true;
	}

	public int compareTo(TemplateSection arg0) {
		return (arg0.getSortOrder() > getSortOrder() ? -1 : (arg0.getSortOrder() < getSortOrder()? 1 : 0));
	}
	
	
}
