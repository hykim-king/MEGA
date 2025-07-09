package com.pcwk.ehr.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.FoodDTO;

@Mapper
public interface FoodMapper extends WorkDiv<FoodDTO> {
	
	/*
	 * 다건등록
	 * @return 1(성공)/0(실패)
	 */
	int saveAll();
	
	/**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();

	
	int isExistByName(String foodName);
	
}
