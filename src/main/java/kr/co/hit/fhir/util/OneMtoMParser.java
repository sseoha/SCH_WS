package kr.co.hit.fhir.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OneMtoMParser {

	JsonNode omtmjson = null;

	public void omtmReceiver(String obj){
		ObjectMapper mapper = new ObjectMapper();
		try {
			omtmjson = mapper.readTree(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendParam();
		new ObsFHIRGenerator().sendURL();
	}

	public void sendParam(){
		ObservationParam.id = getLBL();
		ObservationParam.hdp = getHDP();
		ObservationParam.ldp = getLDP();
		ObservationParam.pulse = getPulse();
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