package com.pcwk.ehr.board.service;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public interface L_ReactionService extends WorkDiv<L_ReactionDTO>{

	int getCount(L_ReactionDTO param);

	int toggleReaction(L_ReactionDTO reactionDTO);
	
	L_ReactionDTO getUserReaction(L_ReactionDTO param);

	
	
}
