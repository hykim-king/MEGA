package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.FreeBoardDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FreeBoardMapper;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	FreeBoardMapper mapper;
	
	public FreeBoardServiceImpl() {
	}
	
	
	@Override
	public List<FreeBoardDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(FreeBoardDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(FreeBoardDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public int doSave(FreeBoardDTO param) {
		return mapper.doSave(param);
		
	}
	
	@Override
	public FreeBoardDTO doSelectOne(FreeBoardDTO param) {
		//단건 조회 + 조회 count증가
		log.debug("param:{}",param);
		int flag = mapper.viewCount(param);
		
		log.debug("flag:{}",flag);
		
		return mapper.doSelectOne(param);
	}


}
