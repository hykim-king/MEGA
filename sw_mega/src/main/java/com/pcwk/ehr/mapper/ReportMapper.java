package com.pcwk.ehr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface ReportMapper extends WorkDiv<ReportDTO> {

	List<ReportDTO> doRetrieve(SearchDTO search);

	ReportDTO doSelectOne(int reportCode);

	/**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();

	
}
