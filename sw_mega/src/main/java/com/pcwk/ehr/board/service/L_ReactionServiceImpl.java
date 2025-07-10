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
			} else {
				return 1; // 취소할 게 없지만 성공
			}
		} else {
			if (exist != null) {
				// 같은 타입의 반응을 이미 눌렀으면 삭제 (취소)
				return mapper.doDelete(exist);
			} else {
				// 새로운 반응 추가
				return mapper.doSave(reactionDTO);
			}
		}
	}

}
