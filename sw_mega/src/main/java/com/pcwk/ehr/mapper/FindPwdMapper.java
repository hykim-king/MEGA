package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.FindPwdDTO;

@Mapper
public interface FindPwdMapper {
	String findPwd(FindPwdDTO dto);

}
