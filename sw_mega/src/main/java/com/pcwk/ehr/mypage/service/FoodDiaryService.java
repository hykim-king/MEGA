package com.pcwk.ehr.mypage.service;

import java.util.List;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;

public interface FoodDiaryService  {

	List<FoodDiaryOutDTO> doRetrieve(FoodDiaryDTO param);

	int doDelete(FoodDiaryDTO param);

	int doUpdate(FoodDiaryDTO param);

	int doSave(FoodDiaryDTO param);
	
	NutritionSummaryDTO getDailySummary(FoodDiaryDTO param);

}
