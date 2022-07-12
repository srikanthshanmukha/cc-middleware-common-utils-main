package com.uaic.cc.middleware.common.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonObjectMapper {
	@JsonIgnore
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	static { jsonMapper.findAndRegisterModules(); }
	
	public static final ObjectMapper getInstance() {
		return jsonMapper;
	}
}
