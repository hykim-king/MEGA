package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FbCommentMapper;
@Service
public class FreeBoardCommentServiceImpl implements FreeBoardCommentService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	FbCommentMapper mapper;
	
	public FreeBoardCommentServiceImpl() {
	}
	
	
	@Override
	public List<FreeBoardCommentDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(FreeBoardCommentDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(FreeBoardCommentDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public FreeBoardCommentDTO doSelectOne(FreeBoardCommentDTO param) {
		return mapper.doSelectOne(param);

	}

	@Override
	public int doSave(FreeBoardCommentDTO param) {
		return mapper.doSave(param);

	}

}
