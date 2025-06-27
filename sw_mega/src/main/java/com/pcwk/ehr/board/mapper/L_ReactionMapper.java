package com.pcwk.ehr.board.mapper;

import com.pcwk.ehr.board.domain.L_ReactionDTO;

public interface L_ReactionMapper {
	
	int insertReaction(L_ReactionDTO vo); //좋아요/싫어요 추가

	int deleteReaction(L_ReactionDTO vo);
	
	int selectReaction(L_ReactionDTO vo); //좋아요 수 확인

	int countReactions(L_ReactionDTO vo); // 특정 게시글의 좋아요/싫어요 수
}
