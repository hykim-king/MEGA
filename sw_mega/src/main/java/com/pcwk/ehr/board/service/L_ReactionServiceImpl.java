package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.L_ReactionMapper;

@Service
public class L_ReactionServiceImpl implements L_ReactionService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	L_ReactionMapper mapper;
	
	
	@Override
	public List<L_ReactionDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(L_ReactionDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(L_ReactionDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public L_ReactionDTO doSelectOne(L_ReactionDTO param) {
		return mapper.doSelectOne(param);

	}

	@Override
	public int doSave(L_ReactionDTO param) {
		return mapper.doSave(param);

	}

}
