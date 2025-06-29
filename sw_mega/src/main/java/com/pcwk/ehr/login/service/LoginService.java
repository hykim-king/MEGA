package com.pcwk.ehr.login.service;

import com.pcwk.ehr.login.domain.LoginDTO;

public interface LoginService {
	LoginDTO login(String userId, String password);
}
