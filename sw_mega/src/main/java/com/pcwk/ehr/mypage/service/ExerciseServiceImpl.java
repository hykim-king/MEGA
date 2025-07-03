package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;


@Service
public class ExerciseServiceImpl implements ExerciseService {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ExerciseMapper mapper;
	
	@Override
	public List<ExerciseDTO> doRetrieve(SearchDTO param) {
		// TODO Auto-generated method stub
		return mapper.doRetrieve(param);
	}

	@Override
	public int doDelete(ExerciseDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(ExerciseDTO param) {
		// TODO Auto-generated method stub
		return mapper.doUpdate(param);
	}

	@Override
	public ExerciseDTO doSelectOne(ExerciseDTO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(ExerciseDTO param) {
		// TODO Auto-generated method stub
		return mapper.doSave(param);
	}

}
