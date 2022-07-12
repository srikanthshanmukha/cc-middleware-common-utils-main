package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import java.util.HashMap;
import java.util.Map;

public class TemplateSectionExecutionResult {

	protected int sortOrder;
	protected String result;
	protected Map<String,String> metadata;
	
	public TemplateSectionExecutionResult() {
		super();
	}

	public TemplateSectionExecutionResult(int sortOrder, String result, Map<String, String> metadata) {
		super();
		this.sortOrder = sortOrder;
		this.result = result;
		this.metadata = metadata;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, String> getMetadata() {
		if(metadata == null) metadata = new HashMap<String, String>();
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	
	
}
