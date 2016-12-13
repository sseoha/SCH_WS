package kr.co.hit.lvlab.mob.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.hit.lvlab.mob.sample.service.RestSampleService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("restSampleService")
public class RestSampleServiceImpl extends EgovAbstractServiceImpl implements RestSampleService {
	
	// ibatis 사용
	//@Resource(name = "restSampleDAO")
	//private RestSampleDAO restSampleDAO;
	
	// TODO mybatis 사용
	@Resource(name="restSampleMapper")
	private RestSampleMapper restSampleDAO;
	
	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectSampleList(Map paramMap) throws Exception {
		return restSampleDAO.selectRestSampleList(paramMap);
	}
}