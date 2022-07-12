package com.uaic.cc.middleware.common.utils.credentials.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uaic.cc.middleware.common.utils.credentials.entities.Credential;
import com.uaic.cc.middleware.common.utils.credentials.entities.Credentials;

class CredentialsTest {

	@Test
	void testToJson() {
		String expected = "{\"credentials\":[{\"id\":\"SECRET_CREDENTIAL_1\",\"username\":\"someuser1\",\"password\":\"somepassword1\"},{\"id\":\"SECRET_CREDENTIAL_2\",\"username\":\"someuser2\",\"password\":\"somepassword2\"},{\"id\":\"SECRET_CREDENTIAL_3\",\"username\":\"someuser3\",\"password\":\"somepassword3\"}]}";
		Credential c1 = new Credential("SECRET_CREDENTIAL_1", "someuser1", "somepassword1");
		Credential c2 = new Credential("SECRET_CREDENTIAL_2", "someuser2", "somepassword2");
		Credential c3 = new Credential("SECRET_CREDENTIAL_3", "someuser3", "somepassword3");
		
		Credentials creds = new Credentials();
		creds.getCredentials().add(c1);
		creds.getCredentials().add(c2);
		creds.getCredentials().add(c3);
		
		try {
			assertEquals(expected, creds.toJson());
		} catch (JsonProcessingException e) {
			fail(e);
		}
		
	}


}
