package com.uaic.cc.middleware.common.utils.templates.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.templates.simple.TemplateFactory;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSectionExecutionResult;

class TemplateFactoryTest {

	@Test
	void testLoad() {
		TemplateFactory tFactory = new TemplateFactory("templatefactory/templates.json");
		
		try {
			tFactory.load();
		} catch (JsonMappingException e) {
			fail(e);
		} catch (JsonProcessingException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
		
		assertEquals(1, tFactory.getTemplateCache().size());
		assertNotNull(tFactory.getTemplate("template 1"));
		assertEquals(2, tFactory.getTemplate("template 1").getSections().size());

	}

	@Test
	void testGetTemplate() {
		TemplateFactory tFactory = new TemplateFactory("templatefactory/templates.json");
		
		try {
			tFactory.load();
		} catch (JsonMappingException e) {
			fail(e);
		} catch (JsonProcessingException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
		
		assertEquals(1, tFactory.getTemplateCache().size());
		assertNotNull(tFactory.getTemplate("template 1"));
	}

	@Test
	void testExecuteTemplate() {
		TemplateFactory tFactory = new TemplateFactory("templatefactory/templates.json");
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("p1", "1");
		values.put("p5", "1");
		values.put("p7", "2");
		
		try {
			tFactory.load();
		} catch (JsonMappingException e) {
			fail(e);
		} catch (JsonProcessingException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
		
		ArrayList<TemplateSectionExecutionResult> result = (ArrayList<TemplateSectionExecutionResult>) tFactory.executeTemplate("template 1", values);
		
		assertEquals("This is the first statement: 1 == 1 and 2 == 2", result.get(0).getResult());
		assertEquals("This is the second statement: 1 == 1", result.get(1).getResult());
		
	}
	
	@Test
	void testUninitializedFactoryThrowsException() {
		TemplateFactory tFactory = new TemplateFactory("templatefactory/templates.json");
		try {
			tFactory.getTemplate("");
		} catch(IllegalStateException e) {
			assertEquals("Factory has not been initialized. method load() must be invoked before this operation", e.getMessage());
			return;
		}
		
		fail("Exception not thrown");
	}

}
