package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.FindIdDTO;

@Mapper
public interface FindIdMapper {
	String findUserId(String email);
}
