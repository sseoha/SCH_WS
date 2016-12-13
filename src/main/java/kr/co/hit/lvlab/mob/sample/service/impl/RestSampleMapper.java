package kr.co.hit.lvlab.mob.sample.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("restSampleMapper")
public interface RestSampleMapper {

	List<?> selectRestSampleList(Map paramMap) throws Exception;
}
