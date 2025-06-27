package com.pcwk.ehr.board.mapper;

import java.util.List;

import com.pcwk.ehr.board.domain.ReportDTO;

public interface ReportMapper {

	 int insertReport(ReportDTO report);

	    List<ReportDTO> selectReportsByUserId(String userId);

	    // (선택) 관리자용 전체 목록
	    List<ReportDTO> selectAllReports();
	
}
