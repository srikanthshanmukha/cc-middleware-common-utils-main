package com.uaic.cc.middleware.common.utils.templates.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.samskivert.mustache.Mustache;
import com.uaic.cc.middleware.common.utils.file.FileUtils;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Template;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSection;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSectionExecutionResult;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Templates;

public class TemplateFactory {
	
	
	private static final Logger log = LoggerFactory.getLogger(TemplateFactory.class);

	protected String resourcePath;
	protected HashMap<String, Template> templateCache;
	protected boolean isInitialized = false;
	
	public TemplateFactory(String resourcePath) {
		this.resourcePath = resourcePath;
		templateCache = new HashMap<String, Template>();
	}
	
	public void load() throws JsonMappingException, JsonProcessingException, IOException {
		if(getResourcePath() == null || getResourcePath().isBlank())
			throw new IllegalStateException("resourcePath is null or blank");
		
		log.info("Loading templates from classpath resource [" + getResourcePath() + "]");
		
		Templates templates = Templates.fromJson(getContent(resourcePath));
		initTemplates(templates);
		
		isInitialized = true;
		
		return;
	}

	protected String getContent(String resourcePath) throws IOException {
		if(resourcePath.startsWith("classpath:")) {
			String classpathReource = resourcePath.split("classpath:")[1];
			return FileUtils.getFileContentFromClasspath(classpathReource);
		} else {
			return FileUtils.getFileContent(resourcePath);
		}
	}
	
	public String getResourcePath() {
		return resourcePath;
	}
	
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

		
	public HashMap<String, Template> getTemplateCache() {
		return templateCache;
	}

	protected void initTemplates(Templates templates) throws JsonProcessingException {
		if(templates.getTemplates() == null || templates.getTemplates().isEmpty())
			throw new IllegalStateException("Resource [" + getResourcePath() + "] is empty or contains no templates");
		
		for(Template t : templates.getTemplates()) {
			// check for dups
			if(getTemplateCache().get(t.getId()) != null) {
				log.warn("Template with ID [" + t.getId() + "] is duplicated. The last version will be loaded instead");
				log.warn("Previous version: " + getTemplateCache().get(t.getId()).toJson());
				log.warn("Latest version:" + t.toJson());
			}
			
			getTemplateCache().put(t.getId(), t);
		}
		
		log.info("Loaded " + getTemplateCache().size() + " templates from classpath resource [" + getResourcePath() + "]");
		
		return;
	}
	
	public Template getTemplate(String id) {
		if(!isInitialized) throw new IllegalStateException("Factory has not been initialized. method load() must be invoked before this operation");
		return getTemplateCache().get(id);
	}
	
	public List<TemplateSectionExecutionResult> executeTemplate(String templateId, Map<String, String> paramValues) {
		if(!isInitialized) throw new IllegalStateException("Factory has not been initialized. method load() must be invoked before this operation");
		ArrayList<TemplateSectionExecutionResult> result = new ArrayList<TemplateSectionExecutionResult>();
		Template t = getTemplate(templateId);
		
		if(t == null)
			throw new IllegalArgumentException("Unknown templateId [" + templateId + "]");
		
		for(TemplateSection ts : t.getSections()) {
			com.samskivert.mustache.Template tmpl = Mustache.compiler().compile(ts.getStencil());
			
			if(!(paramValues.keySet().containsAll(ts.getRequiredParameters()))) {
				String missingParams = "";
				for(String param : ts.getRequiredParameters())
					if(! paramValues.containsKey(param))
						missingParams += param + " ";
				throw new IllegalArgumentException("Template section for template [" + templateId + "] with sort order [" + ts.getSortOrder() + "] missing required parameters [" + missingParams + "]");
			}
			
			result.add(new TemplateSectionExecutionResult(ts.getSortOrder(), tmpl.execute(paramValues), ts.getMetadata()) );
		}
		
		return result;
	}
}
