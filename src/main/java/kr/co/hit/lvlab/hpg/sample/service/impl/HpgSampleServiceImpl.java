package kr.co.hit.lvlab.hpg.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.hit.lvlab.hpg.sample.service.HpgSampleService;


@Service("hpgSampleService")
public class HpgSampleServiceImpl extends EgovAbstractServiceImpl implements HpgSampleService{

	@Resource(name="hpgSampleMapper")
	private HpgSampleMapper hpgSampleMapper;
	
	@Override
	public List<?> selectSampleList(Map<String, String> paramMap) throws Exception{
		//System.out.println("Hello eGov World!");
		//return null;
		
		System.out.println("Mapper");
		return hpgSampleMapper.selectSampleList(paramMap);
	}
}

