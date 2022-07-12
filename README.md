# Common utilities

This library contains simplified common utilities for the following:

 - File utilities to read from file path or classpath resource
 - A simple [Mustach](https://github.com/samskivert/jmustache) based template factory and executor (loads templates from classpath)
 - A simple credentials factory to load creds from file path

## File Utilities

`FileUtils` has 2 static methods that return the file contents as string:
```
String getFileContentFromClasspath(String fName)
String getFileContent(String fName)
```

## Simple Template Factory

`TemplateFactory` relies on a templates definition to be loaded from classpath resource and provides the method `executeTemplate(String templateId, HashMap<String, String> paramValues)` to return the result of processing a template (specified by `templateId`).

**IMPORTANT:** *The method `load()` must be invoked after instantiation and before any other method invocation.*

### Template file structure
```
{
   "templates":[
      {
         "id":"template 1",
         "description":"Template 1 description",
         "sections":[
            {
               "sortOrder":5,
               "stencil":"sample {{stencil2}} with curlies",
               "metadata":{
                  "TARGET":"DB2_DATA_QUEUE",
                  "SOME_OTHER_META_DATA":"some value"
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            },
            {
               "sortOrder":10,
               "stencil":"sample {{stencil}} with curlies",
               "metadata":{
                  "TARGET":"DB2_DATA_QUEUE",
                  "SOME_OTHER_META_DATA":"some value"
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            }
         ]
      },
      {
         "id":"template 2",
         "description":"Template 1 description",
         "sections":[
            {
               "sortOrder":5,
               "stencil":"sample {{stencil2}} with curlies",
               "metadata":{
                  "TARGET":"DB2_DATA_QUEUE",
                  "SOME_OTHER_META_DATA":"some value"
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            },
            {
               "sortOrder":10,
               "stencil":"sample {{stencil}} with curlies",
               "metadata":{
                  "TARGET":"DB2_DATA_QUEUE",
                  "SOME_OTHER_META_DATA":"some value"
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            }
         ]
      },
      {
         "id":"template 3",
         "description":"Template 1 description",
         "sections":[
            {
               "sortOrder":5,
               "stencil":"sample {{stencil2}} with curlies",
               "metadata":{
                  
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            },
            {
               "sortOrder":10,
               "stencil":"sample {{stencil}} with curlies",
               "metadata":{
                  
               },
               "requiredParameters":[
                  "p1",
                  "p2",
                  "p3",
                  "p4"
               ]
            }
         ]
      }
   ]
}
```
### Usage
```
		TemplateFactory tFactory = new TemplateFactory("templatefactory/templates.json");
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("p1", "1");
		values.put("p5", "1");
		values.put("p7", "2");
		
		tFactory.load();
		
		ArrayList<String> result = tFactory.executeTemplate("template 1", values);
```
Where `result` array will have the same order as per `section.sortOrder`

## Credential Factory

`CredentialFactory` relies on a credentials definition to be loaded from a file resource and provides the method `getCredential(String id)` to return the credential defined for `id`.

**IMPORTANT:** *The method `load()` must be invoked after instantiation and before any other method invocation.*

### Credentials file structure
```
{
	"credentials": [
		{
			"id": "SECRET_CREDENTIAL_1",
			"username": "someuser1",
			"password": "somepassword1"
		},
		{
			"id": "SECRET_CREDENTIAL_2",
			"username": "someuser2",
			"password": "somepassword2"
		},
		{
			"id": "SECRET_CREDENTIAL_3",
			"username": "someuser3",
			"password": "somepassword3"
		}
	]
}
```
### Usage
```
	CredentialFactory cFactory = new CredentialFactory("somefilepath");
	cFactory.load();
	Credential c = cFactory.getCredential("SECRET_CREDENTIAL_2");
```

