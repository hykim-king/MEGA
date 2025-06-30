package com.pcwk.ehr.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.dao.LoginDao;
import com.pcwk.ehr.login.domain.LoginDTO;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
    LoginDao loginDao;

    @Override
    public LoginDTO doSelectOne(LoginDTO dto) {
        return loginDao.doSelectOne(dto);
    }

}
