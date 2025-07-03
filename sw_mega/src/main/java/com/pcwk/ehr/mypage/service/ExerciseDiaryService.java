package com.pcwk.ehr.mypage.service;

import java.util.List;

import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.ExerciseSummaryDTO;

public interface ExerciseDiaryService {

	List<ExerciseDiaryOutDTO> doRetrieve(ExerciseDiaryDTO param);
	
	int doDelete(ExerciseDiaryDTO param);
	
	int doUpdate(ExerciseDiaryDTO param);
	
	int doSave(ExerciseDiaryDTO param);
	
	ExerciseSummaryDTO getDailySummary(ExerciseDiaryDTO param);
}
