package kr.co.hit.lvlab.hpg.sample.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("hpgSampleMapper")
public interface HpgSampleMapper {
	List<?> selectSampleList(Map<String, String> paramMap) throws Exception; 
}
