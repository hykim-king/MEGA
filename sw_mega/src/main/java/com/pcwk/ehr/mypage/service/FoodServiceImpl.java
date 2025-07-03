package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mypage.domain.FoodDTO;

@Service
public class FoodServiceImpl implements FoodService {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	FoodMapper mapper;
	
	public FoodServiceImpl() {
	}
	
	@Override
	public List<FoodDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);
	}

	@Override
	public int doDelete(FoodDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(FoodDTO param) {
		return mapper.doUpdate(param);
	}

	@Override
	public FoodDTO doSelectOne(FoodDTO param) {
		
		FoodDTO dbData = mapper.doSelectOne(param);
		
		double ratio = param.getGrams() / (double) dbData.getStGrams();

		dbData.setGrams(param.getGrams()); // 사용자가 입력한 g
		dbData.setTotalCal(Math.round(dbData.getCal() * ratio * 10) / 10.0);
		dbData.setTotalCarb(Math.round(dbData.getCarb() * ratio * 10) / 10.0);
		dbData.setTotalFat(Math.round(dbData.getFat() * ratio * 10) / 10.0);
		dbData.setTotalProt(Math.round(dbData.getProt() * ratio * 10) / 10.0);

		return dbData;
	}

	@Override
	public int doSave(FoodDTO param) {
		return mapper.doSave(param);
	}


}
