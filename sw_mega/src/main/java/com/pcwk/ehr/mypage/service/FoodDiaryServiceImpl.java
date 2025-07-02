package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;
import com.pcwk.ehr.mypage.mapper.FoodDiaryMapper;

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
		
	    List<FoodDiaryOutDTO> rawList = mapper.doRetrieve(param); // JOIN된 기본 데이터

	    for (FoodDiaryOutDTO dto : rawList) {
	        double ratio = dto.getGrams() / (double) dto.getStGrams();

	        dto.setTotalCal(Math.round(dto.getCal() * ratio * 10) / 10.0);
	        dto.setTotalCarb(Math.round(dto.getCarb() * ratio * 10) / 10.0);
	        dto.setTotalFat(Math.round(dto.getFat() * ratio * 10) / 10.0);
	        dto.setTotalProt(Math.round(dto.getProt() * ratio * 10) / 10.0);
	        dto.setTotalNa(Math.round(dto.getNa() * ratio * 10) / 10.0);
	    }

	    return rawList;
	}
	
	@Override
	public NutritionSummaryDTO getDailySummary(FoodDiaryDTO param) {
	    List<FoodDiaryOutDTO> list = mapper.doRetrieve(param);

	    double cal = 0;
	    double carb = 0;
	    double fat = 0;
	    double prot = 0;
	    double na = 0;

	    for (FoodDiaryOutDTO dto : list) {
	        double ratio = dto.getGrams() / (double) dto.getStGrams();

	        cal += dto.getCal() * ratio;
	        carb += dto.getCarb() * ratio;
	        fat += dto.getFat() * ratio;
	        prot += dto.getProt() * ratio;
	        na += dto.getNa() * ratio;
	    }

	    NutritionSummaryDTO summary = new NutritionSummaryDTO();
	    summary.setUserId(param.getUserId());
	    summary.setRegDt(param.getRegDt());
	    summary.setTotalCal(Math.round(cal * 10) / 10.0);
	    summary.setTotalCarb(Math.round(carb * 10) / 10.0);
	    summary.setTotalFat(Math.round(fat * 10) / 10.0);
	    summary.setTotalProt(Math.round(prot * 10) / 10.0);
	    summary.setTotalNa(Math.round(na * 10) / 10.0);

	    return summary;
	}


}
