package com.pcwk.ehr.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.dao.FindPwdDao;
import com.pcwk.ehr.login.domain.FindPwdDTO;

@Service
public class FindPwdServiceImpl implements FindPwdService {

	@Autowired
    FindPwdDao findPwdDao;

    @Override
    public String findPwd(FindPwdDTO dto) {
    	return findPwdDao.findPwd(dto);
    }

}
