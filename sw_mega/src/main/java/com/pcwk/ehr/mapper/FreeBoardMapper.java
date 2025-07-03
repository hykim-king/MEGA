package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.FreeBoardDTO;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface FreeBoardMapper extends WorkDiv<FreeBoardDTO>{
	/**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();
	
	/**
	 * 조회수 증가
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int viewCount(FreeBoardDTO param);

}
