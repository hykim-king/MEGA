package com.pcwk.ehr.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.domain.FindPwdDTO;
@Service
public class FindPwdDaoImpl implements FindPwdDao {

	private final String NAMESPACE = "com.pcwk.ehr.findpwd.FindPwdDao";
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int updatePassword(FindPwdDTO dto) {
        return sqlSessionTemplate.update(NAMESPACE + ".updatePassword", dto);
    }
}
