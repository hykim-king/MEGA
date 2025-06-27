package com.pcwk.ehr.findid;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FindIdDaoImpl implements FindIdDao {

	private final String NAMESPACE = "com.pcwk.ehr.findid.FindIdDao";
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public String findUserId(FindIdDTO dto) {
        return sqlSessionTemplate.selectOne(NAMESPACE + ".findUserId", dto);
    }

}
