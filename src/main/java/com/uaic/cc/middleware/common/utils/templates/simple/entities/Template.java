package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;

public class Template {

	protected String id;
	protected String description;
	protected SortedSet<TemplateSection> sections;

	public Template() {
		super();
		sections = new TreeSet<TemplateSection>();//(TemplateSection.comparator());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id == null) id = "";
		this.id = id;
	}

	public String getDescription() {
		if(description == null) description = "";
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SortedSet<TemplateSection> getSections() {
		if(sections == null) sections = new TreeSet<TemplateSection>();//(TemplateSection.comparator());
		return sections;
	}

	public void setSections(SortedSet<TemplateSection> sections) {
		this.sections = sections;
	}
	
	@JsonIgnore
	public String toJson() throws JsonProcessingException {
		return JsonObjectMapper.getInstance().writeValueAsString(this);
	}

	@JsonIgnore
	public static Template fromJson(String json) throws JsonMappingException, JsonProcessingException {
		if(json == null || json.trim().isBlank()) return null;
		return JsonObjectMapper.getInstance().readValue(json, Template.class);
	}

	/***
	 * NOTE: description is not used for equality comparison
	 */
	@Override
	public boolean equals(Object obj) {
		Template t = (Template) obj;
		
		if(! getId().equalsIgnoreCase(t.getId())) return false;
		if(! getSections().equals(t.getSections())) return false;

		return true;
	}	
	
	
}
