package com.uaic.cc.middleware.common.utils.credentials;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.uaic.cc.middleware.common.utils.credentials.entities.Credential;
import com.uaic.cc.middleware.common.utils.credentials.entities.Credentials;
import com.uaic.cc.middleware.common.utils.file.FileUtils;

public class CredentialFactory {

	
	private static final Logger log = LoggerFactory.getLogger(CredentialFactory.class);

	protected String secretsFilePath;
	protected HashMap<String, Credential> credCache;
	protected boolean isInitialized = false;
	
	public CredentialFactory(String secretsFilePath) {
		this.secretsFilePath = secretsFilePath;
		credCache = new HashMap<String, Credential>();
	}
	
	
	public String getSecretsFilePath() {
		return secretsFilePath;
	}


	public void setSecretsFilePath(String secretsFilePath) {
		this.secretsFilePath = secretsFilePath;
	}
	
	protected HashMap<String, Credential> getCredCache() {
		if(credCache == null) credCache = new HashMap<String, Credential>();
		return credCache;
	}

	public void load() throws JsonMappingException, JsonProcessingException, IOException {
		if(getSecretsFilePath() == null || getSecretsFilePath().isBlank())
			throw new IllegalStateException("secretsFilePath is null or blank");
		
		log.info("Loading secrets from file [" + getSecretsFilePath() + "]");
		
		Credentials creds = Credentials.fromJson(FileUtils.getFileContent(getSecretsFilePath()));
		initCreds(creds);
		
		isInitialized = true;
		
		return;
	}


	protected void initCreds(Credentials creds) {
		if(creds.getCredentials() == null || creds.getCredentials().isEmpty())
			throw new IllegalStateException("File [" + getSecretsFilePath() + "] is empty or contains no credentials");
		
		for(Credential c : creds.getCredentials()) {
			// check for dups
			if(getCredCache().get(c.getId()) != null) {
				log.warn("Credential with ID [" + c.getId() + "] is duplicated. The last version will be loaded instead");
			}

			getCredCache().put(c.getId(), c);
		}
		
		log.info("Loaded " + getCredCache().size() + " crednetials from file resource [" + getSecretsFilePath() + "]");
		
		return;
	}
	
	public Credential getCredential(String id) {
		if(!isInitialized) throw new IllegalStateException("Factory has not been initialized. method load() must be invoked before this operation");
		return getCredCache().get(id);
	}
}
