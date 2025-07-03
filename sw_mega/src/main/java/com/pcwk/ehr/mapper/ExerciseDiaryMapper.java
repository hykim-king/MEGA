package com.pcwk.ehr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;

@Mapper
public interface ExerciseDiaryMapper extends WorkDiv<ExerciseDiaryDTO>{
	
    // doRetrieve를 별도로 정의
    List<ExerciseDiaryOutDTO> doRetrieve(ExerciseDiaryDTO param);
    
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

}
