package kr.co.hit.fhir.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ObsFHIRGenerator {
	
	String id = null;
	String hdp = null;
	String ldp = null;
	String pulse = null;
	
	public void sendtoParser(String lbl, String hdp, String ldp, String pulse){

		ObjectMapper mapper = new ObjectMapper();
		ObsFHIRVO observation = createObservation();
		
		id = lbl;
		this.hdp = hdp;
		this.ldp = ldp;
		this.pulse = pulse;

		try {

			String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(observation);
			URL sendurl = new URL("http://schws-ljhtest.paas.platform-unit.co.kr/sample-3.5.0/fhir/sendtoparser");
			HttpURLConnection connection = (HttpURLConnection) sendurl.openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true); 				// 데이터 넘겨주기 가능
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(false);  //Redirect처리 하지 않음
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os= connection.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			
			connection.getResponseCode();
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
	
	private ObsFHIRVO createObservation(){
		
		String lastUpdated = ObservationParam.lastUpdated;
		String reference = ObservationParam.reference;
		String effectiveDateTime = ObservationParam.effectiveDateTime;
		String bodySite = ObservationParam.bodySite;
		
		ObsFHIRVO observation = new ObsFHIRVO();
		observation.setId(id);
		
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("lastUpdated", lastUpdated);
		observation.setMeta(meta);
		
		Map<String, Object> subject = new HashMap<String, Object>();
		subject.put("reference", reference);
		observation.setSubject(subject);
		
		observation.setEffectiveDateTime(effectiveDateTime);
		
		Map<String, Object> bodysite = new HashMap<String, Object>();
		List<Object> link = new ArrayList<>();
		Map<String, Object> coding = new HashMap<String, Object>();
		
		coding.put("system", "http://snomed.info/sct");
		coding.put("code","368209003");
		coding.put("display", bodySite);
		link.add(coding);
		bodysite.put("coding", link);
		observation.setBodySite(bodysite);
		
		List<Object> componentlist = new ArrayList<>();
		Map<String, Object> component = new HashMap<String, Object>();
		
		Map<String, Object> codemap = new HashMap<String, Object>();
		List<Object> codinglist = new ArrayList<>();
		Map<String, Object> valueQuantitymap = new HashMap<String, Object>();
		
		Map<String, Object> codingmap = new HashMap<String, Object>();
		
		codingmap.put("system", "http://loinc.org");
		codingmap.put("code", "8480-6");
		codingmap.put("display", "Systolic blood pressure");
		
		codinglist.add(codingmap);
		codemap.put("coding", codinglist);
		
		valueQuantitymap.put("value", hdp);
		valueQuantitymap.put("unit", "mm[Hg]");
		
		component.put("code", codemap);
		component.put("valueQuantity", valueQuantitymap);
		componentlist.add(component);


		Map<String, Object> component2 = new HashMap<String, Object>();
		
		Map<String, Object> codemap2 = new HashMap<String, Object>();
		List<Object> codinglist2 = new ArrayList<>();
		Map<String, Object> valueQuantitymap2 = new HashMap<String, Object>();
		
		Map<String, Object> codingmap2 = new HashMap<String, Object>();
		
		codingmap2.put("system", "http://loinc.org");
		codingmap2.put("code", "8462-4");
		codingmap2.put("display", "Diastolic blood pressure");
		
		codinglist2.add(codingmap2);
		codemap2.put("coding", codinglist2);
		
		valueQuantitymap2.put("value", ldp);
		valueQuantitymap2.put("unit", "mm[Hg]");
		
		component2.put("code", codemap2);
		component2.put("valueQuantity", valueQuantitymap2);
		componentlist.add(component2);
		
		
		Map<String, Object> component3 = new HashMap<String, Object>();
		
		Map<String, Object> codemap3 = new HashMap<String, Object>();
		List<Object> codinglist3 = new ArrayList<>();
		Map<String, Object> valueQuantitymap3 = new HashMap<String, Object>();
		
		Map<String, Object> codingmap3 = new HashMap<String, Object>();
		
		codingmap3.put("system", "http://loinc.org");
		codingmap3.put("code", "8462-4");
		codingmap3.put("display", "pulse");
		
		codinglist3.add(codingmap3);
		codemap3.put("coding", codinglist3);
		
		valueQuantitymap3.put("value", pulse);
		valueQuantitymap3.put("unit", "mm[Hg]");
		
		component3.put("code", codemap3);
		component3.put("valueQuantity", valueQuantitymap3);
		componentlist.add(component3);
				
		
		observation.setComponent(componentlist);
		
		return observation;
	}
}
