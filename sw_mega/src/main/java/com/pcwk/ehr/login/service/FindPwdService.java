package com.pcwk.ehr.login.service;

import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.FindPwdDTO;

public interface FindPwdService {
	String findPwd(FindPwdDTO dto);
}
