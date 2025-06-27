package com.pcwk.ehr.findid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindIdServiceImpl {
	 @Autowired
	    FindIdDao findIdDao;
	 
	    public String findUserId(FindIdDTO dto) {
	        return findIdDao.findUserId(dto);
	    }
}
