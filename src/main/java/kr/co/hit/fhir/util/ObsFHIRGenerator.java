package kr.co.hit.fhir.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObsFHIRGenerator {
	
	public void sendURL(){

		ObjectMapper mapper = new ObjectMapper();
		ObsFHIRVO observation = createObservation();

		try {

			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(observation);

			String body = jsonInString;
			URL postUrl = new URL("http://localhost:8080/sample/fhir/sendtoparser");
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true); 				// xml내용을 전달하기 위해서 출력 스트림을 사용
			connection.setInstanceFollowRedirects(false);  //Redirect처리 하지 않음
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os= connection.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			System.out.println("Location: " + connection.getHeaderField("Location"));

//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					(connection.getInputStream())));
//
//			String output;
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println("output = " + output);
//			}
			connection.disconnect();
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ObsFHIRVO createObservation(){
		
		String lastUpdated = ObservationParam.lastUpdated;
		String id = ObservationParam.id;
		String hdp = ObservationParam.hdp;
		String ldp = ObservationParam.ldp;
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
		observation.setComponent(componentlist);
		
		
		return observation;
	}
}
