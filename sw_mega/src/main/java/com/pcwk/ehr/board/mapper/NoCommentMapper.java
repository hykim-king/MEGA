package com.pcwk.ehr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface NoCommentMapper extends WorkDiv<NoticeCommentDTO> {
}
