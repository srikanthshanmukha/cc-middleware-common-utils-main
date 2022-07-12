package deleteme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uaic.cc.middleware.common.jdbcdelegate.vo.JdbcDelegateRequest;
import com.uaic.cc.middleware.common.jdbcdelegate.vo.JdbcDelegateResponse;
import com.uaic.cc.middleware.common.utils.json.JsonObjectMapper;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Template;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.TemplateSection;
import com.uaic.cc.middleware.common.utils.templates.simple.entities.Templates;

public class Play {

	
	private static final Logger log = LoggerFactory.getLogger(Play.class);

	public static void main(String[] args) throws JsonProcessingException {
//		play1();
		jdbcDelegateReqRespSamples();
	}
	
	public static void jdbcDelegateReqRespSamples() throws JsonProcessingException {
		LocalDateTime now = LocalDateTime.now();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("payload1", "( 'UAIC76805308', '01', '0100998877', '101' ,'ClaimAdded','2022-05-09','Portal User')");
		params.put("payload2", "( 'UAIC76805308', '01',  '0100998877', 'UAD', '958555', '2022-05-05', '2022-05-09', 'true','false','FL','inshitclmnt','CORAL GABLES,  FL','Insured vehicle hit claimant vehicle',insuredclaimant@gmail.com','1800','ERICK G.  ACUNA PASTRAN','open','ACUNA ABADIE','','ANDREA','1992-02-24','F412563','')");
		JdbcDelegateRequest req = new JdbcDelegateRequest("Template_123", "UAIC76805308", "01XXXXXXXX", "cc user", now, params);
		
		log.info("REQUEST");
		log.info("================\n" + JsonObjectMapper.getInstance().writeValueAsString(req));
		log.info("=========================================");
		
		JdbcDelegateResponse resp = new JdbcDelegateResponse("UAIC76805308", false, "This would be the error message", now, LocalDateTime.now());
		log.info("RESPONSE");
		log.info("================\n" + JsonObjectMapper.getInstance().writeValueAsString(resp));
		log.info("=========================================");
	}
	
	public static void play1() {
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
		
		Templates templates = new Templates();
		templates.getTemplates().add(t1);
		templates.getTemplates().add(t2);
		templates.getTemplates().add(t3);

		try {
			log.info(templates.toJson());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
