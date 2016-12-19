package kr.co.hit.fhir.web;

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
	public void getObsFHIR(@RequestBody Map<String, Object> map) throws Exception {
		//test
		ObsFHIRParser parser = new ObsFHIRParser();
		parser.obsParserReceiver(map);
		
	}
}
