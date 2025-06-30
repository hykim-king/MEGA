package com.pcwk.ehr.login.service;

import com.pcwk.ehr.login.domain.LoginDTO;

public interface LoginService {
	LoginDTO doSelectOne(LoginDTO dto);
}
