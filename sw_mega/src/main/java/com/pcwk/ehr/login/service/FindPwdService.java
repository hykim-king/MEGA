package com.pcwk.ehr.login.service;

import com.pcwk.ehr.login.domain.FindPwdDTO;

public interface FindPwdService {
	boolean resetPassword(FindPwdDTO dto);
}
