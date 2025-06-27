package com.pcwk.ehr.login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
	 LoginDTO login(String userId, String password);
}
