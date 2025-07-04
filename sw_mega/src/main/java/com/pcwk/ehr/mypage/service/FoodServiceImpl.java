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
	public int doSave(FoodDTO param) {
		return mapper.doSave(param);
	}
	
	/**
	 * 사용자가 입력한 값을 기준 데이터에 적용하여 칼로리 및 영양정보 계산하여 출력
	 */
	@Override //파라미터 값  : 음식명, 섭취 그람
	public FoodDTO doSelectOne(FoodDTO param) {
		
		FoodDTO dbData = mapper.doSelectOne(param);

	    // 소수점 허용하여 정확한 비율 계산
	    double ratio = param.getGrams() / (double) dbData.getStGrams();

	    // 입력한 그람 수 반영
	    dbData.setGrams(param.getGrams());

	    // 각 영양소를 정수형으로 계산 (소수점 반올림 후 int로 저장)
	    dbData.setTotalCal((int) Math.round(dbData.getCal() * ratio));
	    dbData.setTotalCarb((int) Math.round(dbData.getCarb() * ratio));
	    dbData.setTotalFat((int) Math.round(dbData.getFat() * ratio));
	    dbData.setTotalProt((int) Math.round(dbData.getProt() * ratio));
	    dbData.setTotalNa((int) Math.round(dbData.getNa() * ratio)); 

	    return dbData;
	}



}
