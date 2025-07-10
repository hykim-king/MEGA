package com.pcwk.ehr.login.service;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.mapper.FindPwdMapper;

@Service
public class FindPwdServiceImpl implements FindPwdService {
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
	FindPwdMapper findPwdMapper;

	@Override
	public String findPwd(FindPwdDTO dto) {
		log.debug("findPwd() 호출: " + dto.getUserId() + ", " + dto.getEmail());
		return findPwdMapper.findPwd(dto);
	}
	

}
