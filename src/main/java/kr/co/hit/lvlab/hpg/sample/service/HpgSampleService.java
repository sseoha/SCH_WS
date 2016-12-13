package kr.co.hit.lvlab.hpg.sample.service;

import java.util.List;
import java.util.Map;

public interface HpgSampleService {
	public List<?> selectSampleList(Map<String, String> paramMap) throws Exception;
}

