package kr.co.hit.fhir.web;

import java.util.Map;

import kr.co.hit.fhir.util.OneMtoMParser;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OneMtoMRestController {
	
	@RequestMapping(value = "/onemtom", method = RequestMethod.POST)
	public void getOneMtoMJson(@RequestBody Map<String, Object> map) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		OneMtoMParser onemtom = new OneMtoMParser();
		
		onemtom.omtmReceiver(mapper.writeValueAsString(map));
	}	
}