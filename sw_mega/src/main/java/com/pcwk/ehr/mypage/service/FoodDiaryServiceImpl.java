package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;


@Service
public class FoodDiaryServiceImpl implements FoodDiaryService {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	FoodDiaryMapper mapper;

	@Override
	public int doDelete(FoodDiaryDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(FoodDiaryDTO param) {
		return mapper.doUpdate(param);
	}


	@Override
	public int doSave(FoodDiaryDTO param) {
		return mapper.doSave(param);
	}

	@Override
	public List<FoodDiaryOutDTO> doRetrieve(FoodDiaryDTO param) {
	    
	    List<FoodDiaryOutDTO> rawList = (List<FoodDiaryOutDTO>) mapper.doRetrieve(param); // JOIN된 기본 데이터

	    for (FoodDiaryOutDTO dto : rawList) {
	        double ratio = dto.getGrams() / (double) dto.getStGrams();

	        dto.setTotalCal((int) Math.round(dto.getCal() * ratio));
	        dto.setTotalCarb((int) Math.round(dto.getCarb() * ratio));
	        dto.setTotalFat((int) Math.round(dto.getFat() * ratio));
	        dto.setTotalProt((int) Math.round(dto.getProt() * ratio));
	        dto.setTotalNa((int) Math.round(dto.getNa() * ratio));
	    }

	    return rawList;
	}
	
	@Override
	public NutritionSummaryDTO getDailySummary(FoodDiaryDTO param) {
		
		// 이미 계산된 totalXXX가 있는 리스트를 가져옴
	    List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) this.doRetrieve(param);

	    int cal = 0, carb = 0, fat = 0, prot = 0, na = 0;

	    for (FoodDiaryOutDTO dto : list) {
	        cal += dto.getTotalCal();
	        carb += dto.getTotalCarb();
	        fat += dto.getTotalFat();
	        prot += dto.getTotalProt();
	        na += dto.getTotalNa();
	    }

	    NutritionSummaryDTO summary = new NutritionSummaryDTO();
	    summary.setUserId(param.getUserId());
	    summary.setRegDt(param.getRegDt());
	    summary.setTotalCal(cal);
	    summary.setTotalCarb(carb);
	    summary.setTotalFat(fat);
	    summary.setTotalProt(prot);
	    summary.setTotalNa(na);

	    return summary;
	}


}
