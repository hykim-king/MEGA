package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;

@Mapper
public interface ExerciseMapper extends WorkDiv<ExerciseDTO>{
	
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
	
	int isExist(ExerciseDTO param); // ExerciseDTO에 운동명 + 체중 + 성별 포함되어 있어야 함

}
