package com.pcwk.ehr.board.mapper;

import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.WorkDiv;


public interface NoticeMapper extends WorkDiv<NoticeDTO> {
	
	
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
	int viewCount(NoticeDTO param);
}
