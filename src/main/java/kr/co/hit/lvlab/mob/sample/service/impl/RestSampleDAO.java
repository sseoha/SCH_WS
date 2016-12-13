package kr.co.hit.lvlab.mob.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("restSampleDAO")
public class RestSampleDAO extends EgovAbstractDAO {
	
	/**
	 * 글 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<?> selectSampleList(Map paramMap) throws Exception {
		System.out.println("DAO");
		return list("sampleDAO.selectRestSampleList", paramMap);
	}
}