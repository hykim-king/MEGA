package com.pcwk.ehr.board.mapper;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public interface L_ReactionMapper extends WorkDiv<L_ReactionDTO> {
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();
	
	/**
	 * 전체삭제
	 */
	void deleteAll();

}
