package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.LoginDTO;

@Mapper
public interface LoginMapper {
	LoginDTO doSelectOne(LoginDTO dto);
}
