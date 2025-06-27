package com.pcwk.ehr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface FbCommentMapper extends WorkDiv<FreeBoardCommentDTO> {

}
