package com.pcwk.ehr.login.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.LoginDTO;
import com.pcwk.ehr.mapper.LoginMapper;

@Service(value = "loginServiceImpl")
public class LoginServiceImpl implements LoginService {
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
	LoginMapper loginMapper;

	@Override
	public LoginDTO doSelectOne(LoginDTO dto) {
		log.debug("dto:"+dto);
		return loginMapper.doSelectOne(dto);
	}

}
