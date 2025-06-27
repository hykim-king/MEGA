package com.pcwk.ehr.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
    LoginDao loginDao;

    @Override
    public LoginDTO login(String userId, String password) {
        return loginDao.login(userId, password);
    }

}
