package com.uaic.cc.middleware.common.utils.templates.simple.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uaic.cc.middleware.common.utils.templates.simple.entities.Template;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSection;

class TemplateTest {

	@Test
	@DisplayName("Test Sections Sort Order")
	void testTemplateSectionsSorting() {
		TemplateSection s1 = new TemplateSection();
		TemplateSection s2 = new TemplateSection();
		TemplateSection s3 = new TemplateSection();
		
		s1.setSortOrder(5);
		s1.setStencil("ts 1");
		
		s2.setSortOrder(1);
		s2.setStencil("ts 2");
		
		s3.setSortOrder(15);
		s3.setStencil("ts 3");
		
		Template t = new Template();
		
		t.getSections().add(s1);
		t.getSections().add(s2);
		t.getSections().add(s3);
		
		int i = 0;
		boolean allgood = false;
		
		for(TemplateSection ts : t.getSections()) {
			if(	(i == 0 && ts == s2) ||
				(i == 1 && ts == s1) ||
				(i == 2 && ts == s3)
			  ) {
				allgood = true;
			} else {
				allgood = false;
			}
			i++;		
		}
		assert(allgood);
		
	}

}
