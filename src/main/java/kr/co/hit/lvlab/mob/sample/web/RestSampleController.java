package kr.co.hit.lvlab.mob.sample.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.hit.lvlab.mob.sample.service.RestSampleService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSampleController {

	@Resource(name = "restSampleService")
	private RestSampleService restSampleService;

	@RequestMapping(value = "/api/greeting", method = RequestMethod.POST)
	public Map greetingPost(@RequestBody Map<String, Object> map) throws Exception {

				
		System.out.println("map size=" + map.size());
		System.out.println(map.keySet());

		Map<String, Object> selectedMap = new HashMap<String, Object>();

		selectedMap.put("lastupdated", ((Map) map.get("meta")).get("lastUpdated"));
		selectedMap.put("subject", ((Map) map.get("subject")).get("reference"));
		selectedMap.put("effectiveDateTime", map.get("effectiveDateTime"));
		selectedMap.put("bodySite", ((Map) ((List) ((Map) map.get("bodySite")).get("coding")).get(0)).get("display"));
		selectedMap.put("Systolic blood pressure", String.valueOf(((Map) ((Map) ((List) map.get("component")).get(0)).get("valueQuantity")).get("value"))
								+ " "
								+ String.valueOf(((Map) ((Map) ((List) map.get("component")).get(0)).get("valueQuantity")).get("unit")));
		selectedMap.put("Diastolic blood pressure",String.valueOf(((Map) ((Map) ((List) map.get("component")).get(1)).get("valueQuantity")).get("value"))
								+ " "
								+ String.valueOf(((Map) ((Map) ((List) map.get("component")).get(1)).get("valueQuantity")).get("unit")));

		System.out.println(selectedMap.keySet());

		// selectedMap 내용물 확인
		Iterator selectedkey = selectedMap.keySet().iterator();
		while (selectedkey.hasNext()) {
			String iskey = (String) selectedkey.next();
			System.out.println("key = " + iskey + " val = " + selectedMap.get(iskey));
		}
		
		Map resultMap = new HashMap();
		resultMap.put("return", map);

		return resultMap;
	}

	@RequestMapping(value = "/api/names", method = RequestMethod.POST)
	public List getNames(@RequestBody Map<String, Object> paramMap)
			throws Exception {

		List resultList = restSampleService.selectSampleList(paramMap);
		return resultList;
	}
}