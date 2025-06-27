package com.pcwk.ehr.findpwd;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
