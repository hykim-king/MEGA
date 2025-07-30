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
		L_ReactionDTO exist = mapper.doSelectOne(reactionDTO);

		// 취소 요청이면, 해당 타입만 삭제
		if (reactionDTO.getReactionType() == null) {
			// 여기서는 reactionDTO에 이전 타입을 프론트에서 넘겨주도록 변경
			if (exist != null) {
				return mapper.doDelete(exist);
			}
			return 1;
		}

		// 기존 반응이 동일하면 취소
		if (exist != null) {
			if (reactionDTO.getReactionType().equals(exist.getReactionType())) {
				return mapper.doDelete(exist);
			} else {
				// 다른 반응 → 전환
				return mapper.doUpdate(reactionDTO);
			}
		} else {
			// 새 반응
			return mapper.doSave(reactionDTO);
		}
	}

}
