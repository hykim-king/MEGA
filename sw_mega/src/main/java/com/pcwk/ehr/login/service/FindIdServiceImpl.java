package com.pcwk.ehr.login.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.FindIdDTO;
import com.pcwk.ehr.mapper.FindIdMapper;

@Service
public class FindIdServiceImpl implements FindIdService {
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
    FindIdMapper findIdMapper;

	@Override
	public String findId(FindIdDTO dto) {
		return findIdMapper.findUserId(dto.getEmail());
	}

}
