package kr.co.hit.lvlab.mob.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.SampleDefaultVO;

public interface RestSampleService {

	public List<?> selectSampleList(Map paramMap) throws Exception;
}
