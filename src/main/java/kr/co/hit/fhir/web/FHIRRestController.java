package kr.co.hit.fhir.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import kr.co.hit.fhir.util.ObsFHIRParser;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FHIRRestController {

	@RequestMapping(value = "/fhir/sendtoparser", method = RequestMethod.POST)
	public void greetingPosttt(@RequestBody Map<String, Object> map) throws Exception {
		//public void greetingPost(@RequestBody Map<String, Object> map) throws Exception {

		System.out.println("hihi");
		ObjectMapper om = new ObjectMapper();
		String jsonstr = om.writeValueAsString(map);

		ObsFHIRParser op = new ObsFHIRParser();
		op.observationReceiver(jsonstr);

		System.out.println("=============lastUpdated : " + op.getLastUpdated());
		System.out.println("=============subject : " + op.getSubject());
		System.out.println("=============effectiveDateTime : " + op.getEffectiveDateTime());
		System.out.println("=============bodySite : " + op.getbodySite());
		System.out.println("=============Systolic blood pressure : " +op.getComponent("Systolic blood pressure"));

		//.live로 보내기(공백포함불가)
		BufferedReader in = null;
		String liveurl = null;
		liveurl = "http://localhost:18088/himed2/.live?submit_id=TRZUM01001&business_id=com&userid=seoha&ex_interface=COMBC|053";
		liveurl = liveurl + "&" + "effectiveDateTime=" + op.getEffectiveDateTime();
		liveurl = liveurl + "&" + "bodySite=" + op.getbodySite().replaceAll(" ", "_");
		liveurl = liveurl + "&" + "value=" + op.getComponent("Systolic blood pressure");

		URL obj = new URL(liveurl);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String line;
		while((line = in.readLine()) != null){
			System.out.println(line);
		}
		in.close();
		Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
		resultMap.put("return", map);
		con.disconnect();
	}
}
