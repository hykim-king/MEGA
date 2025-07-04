package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	NoticeMapper mapper;

	public NoticeServiceImpl() {
	}

	@Override
	public List<NoticeDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(NoticeDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(NoticeDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public int doSave(NoticeDTO param) {
		return mapper.doSave(param);
	}

	@Override
	public NoticeDTO doSelectOne(NoticeDTO param) {
		//단건 조회 + 조회 count증가
		log.debug("param:{}",param);
		int flag = mapper.viewCount(param);
		
		log.debug("flag:{}",flag);
		
		return mapper.doSelectOne(param);
		
	}

}
