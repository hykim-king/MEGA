package com.pcwk.ehr.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.LoginDTO;
@Service
public class LoginDaoImpl {
	private final String NAMESPACE = "com.pcwk.ehr.login.LoginDao";

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public LoginDTO login(String userId, String password) {
        LoginDTO inDTO = new LoginDTO();
        inDTO.setUserId(userId);
        inDTO.setPassword(password);

        return sqlSessionTemplate.selectOne(NAMESPACE + ".login", inDTO);
    }
}
