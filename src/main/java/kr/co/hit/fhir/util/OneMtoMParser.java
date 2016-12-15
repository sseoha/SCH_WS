package kr.co.hit.fhir.util;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OneMtoMParser {

	JsonNode omtmjson = null;

	public void omtmReceiver(Map<String, Object> map){
		ObjectMapper mapper = new ObjectMapper();
		try {
			omtmjson = mapper.readTree(mapper.writeValueAsString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("2. omtm Parser 시작");
		new ObsFHIRGenerator().sendtoParser(getLBL(), getHDP(), getLDP(), getPulse());
	}
	
	public String getLBL(){
		return omtmjson.findValue("lbl").asText();		
	}

	public String getHDP(){
		String con = omtmjson.findValue("con").asText();
		int conlen = con.indexOf("hdp");
		return con.substring(con.indexOf(":",conlen)+1,con.indexOf(",",conlen));
	}

	public String getLDP(){
		String con = omtmjson.findValue("con").asText();
		int conlen = con.indexOf("ldp");
		return con.substring(con.indexOf(":",conlen)+1,con.indexOf(",",conlen));
	}

	public String getPulse(){
		String con = omtmjson.findValue("con").asText();
		int conlen = con.indexOf("pulse");
		return con.substring(con.indexOf(":",conlen)+1,con.indexOf("}",conlen));
	}
}