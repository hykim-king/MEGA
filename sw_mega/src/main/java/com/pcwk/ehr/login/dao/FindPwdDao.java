package com.pcwk.ehr.login.dao;

import com.pcwk.ehr.login.domain.FindPwdDTO;

public interface FindPwdDao {
	int updatePassword(FindPwdDTO dto);
}
