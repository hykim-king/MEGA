package com.pcwk.ehr.login.dao;

import com.pcwk.ehr.login.domain.FindIdDTO;


public interface FindIdDao {
	String findUserId(FindIdDTO dto);
}
