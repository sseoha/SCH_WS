package kr.co.hit.fhir.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObsFHIRParser {

	JsonNode test = null;
	JsonNode meta = null;
	JsonNode subject = null;
	JsonNode bodySite = null;
	JsonNode component = null;

	
	public void observationReceiver(String url){
		ObjectMapper om = new ObjectMapper();
		try {
//			test = om.readTree(new File(url));
			test = om.readTree(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//node 분리
		meta = test.path("meta");
		subject = test.path("subject");
		bodySite = test.path("bodySite");
		component = test.path("component");
	}
	
	//component에서 display, value값 map으로 추출
	public String getComponent(String key){
		Map<String, String> map = new HashMap<String, String>();
		
		for(JsonNode node : component){
			String display = node.findValue("display").asText();
			String value = node.findValue("value").asText();
			map.put(display, value);
		}
		return map.get(key);
	}
	
	//location : meta.lastUpdated
	public String getLastUpdated(){
		return meta.path("lastUpdated").asText();
	}
	
	//location : subject.reference
	public String getSubject(){
		return subject.path("reference").asText();
	}
	
	//location : effectiveDateTime
	public String getEffectiveDateTime(){
		return test.findValue("effectiveDateTime").asText();
	}
	
	public String getbodySite(){
		return bodySite.findValue("display").asText();
	}
}
