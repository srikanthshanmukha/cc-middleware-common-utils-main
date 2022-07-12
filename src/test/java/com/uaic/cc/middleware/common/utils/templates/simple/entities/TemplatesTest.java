package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Template;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSection;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Templates;

class TemplatesTest {
	
	static Templates templates = new Templates();
	static String templatesJson = "{\"templates\":[{\"id\":\"template 1\",\"description\":\"Template 1 description\",\"sections\":[{\"sortOrder\":5,\"stencil\":\"sample {{stencil2}} with curlies\",\"metadata\":{\"TARGET\":\"DB2_DATA_QUEUE\",\"SOME_OTHER_META_DATA\":\"some value\"},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]},{\"sortOrder\":10,\"stencil\":\"sample {{stencil}} with curlies\",\"metadata\":{\"TARGET\":\"DB2_DATA_QUEUE\",\"SOME_OTHER_META_DATA\":\"some value\"},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]}]},{\"id\":\"template 2\",\"description\":\"Template 1 description\",\"sections\":[{\"sortOrder\":5,\"stencil\":\"sample {{stencil2}} with curlies\",\"metadata\":{\"TARGET\":\"DB2_DATA_QUEUE\",\"SOME_OTHER_META_DATA\":\"some value\"},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]},{\"sortOrder\":10,\"stencil\":\"sample {{stencil}} with curlies\",\"metadata\":{\"TARGET\":\"DB2_DATA_QUEUE\",\"SOME_OTHER_META_DATA\":\"some value\"},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]}]},{\"id\":\"template 3\",\"description\":\"Template 1 description\",\"sections\":[{\"sortOrder\":5,\"stencil\":\"sample {{stencil2}} with curlies\",\"metadata\":{},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]},{\"sortOrder\":10,\"stencil\":\"sample {{stencil}} with curlies\",\"metadata\":{},\"requiredParameters\":[\"p1\",\"p2\",\"p3\",\"p4\"]}]}]}";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Collection<String> requiredParams = new ArrayList<String>();
		requiredParams.add("p1");
		requiredParams.add("p2");
		requiredParams.add("p3");
		requiredParams.add("p4");
		
		HashMap<String,String> metadata = new HashMap<String, String>();
		metadata.put("TARGET", "DB2_DATA_QUEUE");
		metadata.put("SOME_OTHER_META_DATA", "some value");

		TemplateSection ts = null;
		
		// TEMAPLTE 1
		Template t1 = new Template();
		t1.setId("template 1");
		t1.setDescription("Template 1 description");
		ts = new TemplateSection();
		ts.setSortOrder(10);
		ts.setStencil("sample {{stencil}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		ts.setMetadata(metadata);
		t1.getSections().add(ts);
		ts = new TemplateSection();
		ts.setSortOrder(5);
		ts.setStencil("sample {{stencil2}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		ts.setMetadata(metadata);
		t1.getSections().add(ts);

	
		// TEMAPLTE 2
		Template t2 = new Template();
		t2.setId("template 2");
		t2.setDescription("Template 1 description");
		ts = new TemplateSection();
		ts.setSortOrder(10);
		ts.setStencil("sample {{stencil}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		ts.setMetadata(metadata);
		t2.getSections().add(ts);
		ts = new TemplateSection();
		ts.setSortOrder(5);
		ts.setStencil("sample {{stencil2}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		ts.setMetadata(metadata);
		t2.getSections().add(ts);
		
		// TEMAPLTE 3
		Template t3 = new Template();
		t3.setId("template 3");
		t3.setDescription("Template 1 description");
		ts = new TemplateSection();
		ts.setSortOrder(10);
		ts.setStencil("sample {{stencil}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		t3.getSections().add(ts);
		ts = new TemplateSection();
		ts.setSortOrder(5);
		ts.setStencil("sample {{stencil2}} with curlies");
		ts.getRequiredParameters().addAll(requiredParams);
		t3.getSections().add(ts);
		
		templates.getTemplates().add(t1);
		templates.getTemplates().add(t2);
		templates.getTemplates().add(t3);
		
	}

	@Test
	void testToJson() {
		try {
			assertEquals(templatesJson, templates.toJson());
		} catch (JsonProcessingException e) {
			fail(e);
		}
	}

	@Test
	void testFromJson() {
		try {
			assertEquals(templates, Templates.fromJson(templatesJson) );
		} catch (JsonProcessingException e) {
			fail(e);
		}
	}

}
