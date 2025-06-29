package com.pcwk.ehr.mypage.mapper;

import java.util.List;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;

public interface ExerciseDiaryMapper extends WorkDiv<ExerciseDiaryDTO>{
	
    // doRetrieve를 별도로 정의
    List<ExerciseDiaryOutDTO> doRetrieve(ExerciseDiaryDTO param);

}
