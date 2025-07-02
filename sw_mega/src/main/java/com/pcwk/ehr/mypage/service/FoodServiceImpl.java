package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.mapper.FoodMapper;

@Service
public class FoodServiceImpl implements FoodService {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	FoodMapper mapper;
	
	public FoodServiceImpl() {
	}
	
	@Override
	public List<FoodDTO> doRetrieve(SearchDTO param) {
		
		return null;
	}

	@Override
	public int doDelete(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FoodDTO doSelectOne(FoodDTO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int viewCount(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
