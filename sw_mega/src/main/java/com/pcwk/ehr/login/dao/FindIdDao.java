package com.pcwk.ehr.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.login.domain.FindIdDTO;

@Mapper
public interface FindIdDao {
	String findUserId(FindIdDTO dto);
}
