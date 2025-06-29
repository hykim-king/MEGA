package com.pcwk.ehr.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.dao.FindIdDao;
import com.pcwk.ehr.login.domain.FindIdDTO;

@Service
public class FindIdServiceImpl implements FindIdService{
	 @Autowired
	    FindIdDao findIdDao;
	 @Override
	    public String findId(FindIdDTO dto) {
	        return findIdDao.findUserId(dto);
	    }

}
