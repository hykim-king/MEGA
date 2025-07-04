package com.pcwk.ehr.board.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ReportMapper;

@Service
public class ReportServiceImpl implements ReportService {
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ReportMapper mapper;
	
	@Override
	public List<ReportDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);

	}

	@Override
	public int doDelete(ReportDTO param) {
		return mapper.doDelete(param);

	}

	@Override
	public int doUpdate(ReportDTO param) {
		return mapper.doUpdate(param);

	}

	@Override
	public ReportDTO doSelectOne(ReportDTO param) {
		return mapper.doSelectOne(param);
	}

	@Override
	public int doSave(ReportDTO param) {
		return mapper.doSave(param);

	}

}
