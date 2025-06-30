package com.pcwk.ehr.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.LoginDTO;

@Mapper
public interface LoginMapper {
	 String doSelectOne(LoginDTO dto);
}
