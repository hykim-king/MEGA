package com.pcwk.ehr.login.service;

import javax.mail.MessagingException;

import com.pcwk.ehr.login.domain.FindPwdDTO;

public interface FindPwdService {
	String findPwd(FindPwdDTO dto);
}
