package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.NoCommentMapper;
@Service
public class NoticeCommentSeveiceImpl implements NoticeCommentService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	NoCommentMapper mapper;

	public NoticeCommentSeveiceImpl() {
		
	}
	
	@Override
	public List<NoticeCommentDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(NoticeCommentDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(NoticeCommentDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public NoticeCommentDTO doSelectOne(NoticeCommentDTO param) {
		
		return mapper.doSelectOne(param);
	}

	@Override
	public int doSave(NoticeCommentDTO param) {
		return mapper.doSave(param);
	}


}
