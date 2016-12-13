package kr.co.hit.lvlab.hpg.sample.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.hit.lvlab.hpg.sample.service.HpgSampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class HpgSampleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HpgSampleController.class);
	
	@Resource(name="hpgSampleService")
	private HpgSampleService hpgSampleService;
	
	@RequestMapping(value="/hpg/sample/getSampleList.do")
	public String getSampleList(@RequestParam Map<String, String> paramMap, ModelMap model) throws Exception {
		
		List<?> sampleList = hpgSampleService.selectSampleList(paramMap);
		
		model.addAttribute("resultList", sampleList);
		
		return "hpg/sample/sampleList";
	}
}
