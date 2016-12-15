package kr.co.hit.fhir.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObsFHIRParser {

	JsonNode obsjson = null;
	JsonNode meta = null;
	JsonNode subject = null;
	JsonNode bodySite = null;
	JsonNode component = null;

	public void obsParserReceiver(Map<String, Object> map){
		ObjectMapper mapper = new ObjectMapper();
		try {
			obsjson = mapper.readTree(mapper.writeValueAsString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//node 분리
		meta = obsjson.path("meta");
		subject = obsjson.path("subject");
		bodySite = obsjson.path("bodySite");
		component = obsjson.path("component");
		
		System.out.println("4. fhirparser 시작");
		sendtoLHospital();
	}
	
	public void sendtoLHospital(){
		try{
			//.live로 보내기(공백포함불가)
			//보낼 parameter : 라벨, 수축기혈압, 확장기혈압, 맥박 (4가지)

			URL obj = new URL(obsParam());
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
			
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				System.out.println(line);
			}
			in.close();
			
			con.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(SocketTimeoutException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	//L-hospital 전송 url에 parameter 붙이기
	public String obsParam(){
		System.out.println("5. l-hospital url 시작");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", "000000161");
		map.put("effectiveDateTime", getEffectiveDateTime());
		map.put("bodySite", getbodySite());
		map.put("reference", getSubject());
		map.put("lastUpdated", getLastUpdated());
		map.put("hdp", getComponent("Systolic blood pressure"));
		map.put("ldp", getComponent("Diastolic blood pressure"));
		map.put("pulse", getComponent("pulse"));
		
		String liveurl = "http://210.93.172.246/himed2/.live?submit_id=TRZUM01001&business_id=com&ex_interface=COMBC|053";
		StringBuffer sb = new StringBuffer();
		sb.append(liveurl).append("&");
		
		Iterator<String> keyIterator = map.keySet().iterator();
		
		while(keyIterator.hasNext()){
			String key = keyIterator.next();
			sb.append(key).append("=").append(map.get(key));
			if(keyIterator.hasNext()){
				sb.append("&");
			}
		}
		String url = sb.toString();
		System.out.println(url);
		return url;
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
	
	public String getLastUpdated(){
		return meta.path("lastUpdated").asText();
	}
	
	public String getSubject(){
		return subject.path("reference").asText();
	}
	
	public String getEffectiveDateTime(){
		return obsjson.findValue("effectiveDateTime").asText();
	}
	
	public String getbodySite(){
		return bodySite.findValue("display").asText().replaceAll(" ", "_");
	}
}
