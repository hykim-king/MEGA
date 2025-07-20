package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.L_ReactionMapper;

@Service
public class L_ReactionServiceImpl implements L_ReactionService {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	L_ReactionMapper mapper;

	@Override
	public List<L_ReactionDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(L_ReactionDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(L_ReactionDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public L_ReactionDTO doSelectOne(L_ReactionDTO param) {
		return mapper.doSelectOne(param);

	}

	@Override
	public int doSave(L_ReactionDTO param) {
		return mapper.doSave(param);

	}

	@Override
	public int getCount(L_ReactionDTO param) {
		return mapper.getCount(param);

	}
	
	@Override
	public L_ReactionDTO getUserReaction(L_ReactionDTO param) {
	    return mapper.doSelectOne(param);
	}

	@Override
	@Transactional
	public int toggleReaction(L_ReactionDTO reactionDTO) {
		// 이미 누른 반응 있는지 확인
		L_ReactionDTO exist = mapper.doSelectOne(reactionDTO);

		if (reactionDTO.getReactionType() == null) {
			// sendType=null 이면 취소 → 기존 반응 있으면 삭제
			if (exist != null) {
				return mapper.doDelete(exist);
			} 
				return 1; // 취소할 게 없지만 성공
			
	    }

	    if (exist != null) {
	        if (reactionDTO.getReactionType().equals(exist.getReactionType())) {
	            // 같은 반응 → 토글 끄기
	            return mapper.doDelete(exist);
	        } else {
	            // 다른 반응 → 업데이트 (좋아요 ↔ 싫어요 전환)
	            return mapper.doUpdate(reactionDTO);
	        }
	    } else {
	        // 처음 반응하는 경우
	        return mapper.doSave(reactionDTO);
	    }
	}
}


