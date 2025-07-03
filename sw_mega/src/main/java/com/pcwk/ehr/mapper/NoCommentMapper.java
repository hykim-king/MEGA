package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface NoCommentMapper extends WorkDiv<NoticeCommentDTO> {
	
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
