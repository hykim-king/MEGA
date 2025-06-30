package com.pcwk.ehr.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.LoginDTO;

@Mapper
public interface LoginDao {
	 LoginDTO login(String userId, String password);
}
