package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface L_ReactionMapper extends WorkDiv<L_ReactionDTO> {

	
	/**
	 * 등록 메서드 등 다른 메서드
	 * 
	 */
	int doSave(L_ReactionDTO dto);
	
	/**
	 * 좋아요/싫어요 수 조회용
	 * 
	 */
	int getCount(L_ReactionDTO dto);
	
	//DAOTEST GETCOUNT빨간줄 제거용
	int getCount();
	
	/**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 수정
	 */
	int doUpdate(L_ReactionDTO param);

}
