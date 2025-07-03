package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.mapper.ExerciseDiaryMapper;
import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.ExerciseSummaryDTO;

@Service
public class ExerciseDiaryServiceImpl implements ExerciseDiaryService {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ExerciseDiaryMapper mapper;

	@Override
	public List<ExerciseDiaryOutDTO> doRetrieve(ExerciseDiaryDTO param) {
	    List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) mapper.doRetrieve(param);

	    for (ExerciseDiaryOutDTO dto : list) {
	        int totalCal = 0;
	        String type = dto.getExerciseType();

	        if (type != null && type.equalsIgnoreCase("유산소")) {
	            // 유산소 운동: duration * cal_burn
	            totalCal = (int) (dto.getDuration() * dto.getCalBurn());
	        } else if (type != null && type.equalsIgnoreCase("근력")) {
	            // 근력 운동: strenth_weight * set_count * reps_per_set * cal_burn
	            totalCal = (int) (dto.getStrenthWeight() * dto.getSetCount() * dto.getRepsPerSet() * dto.getCalBurn());
	        }

	        dto.setTotalCalories(totalCal);
	    }

	    return list;
	}

	@Override
	public int doDelete(ExerciseDiaryDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(ExerciseDiaryDTO param) {
		return mapper.doUpdate(param);
	}

	@Override
	public int doSave(ExerciseDiaryDTO param) {
		return mapper.doSave(param);
	}
	
	@Override
	public ExerciseSummaryDTO getDailySummary(ExerciseDiaryDTO param) {
	    // 이미 총칼로리 계산된 리스트
	    List<ExerciseDiaryOutDTO> list = this.doRetrieve(param);

	    int totalCalories = 0;
	    int totalDuration = 0;

	    for (ExerciseDiaryOutDTO dto : list) {
	        totalCalories += dto.getTotalCalories(); // 이미 계산된 값
	        totalDuration += dto.getDuration();      // 그냥 더하면 됨
	    }

	    ExerciseSummaryDTO summary = new ExerciseSummaryDTO();
	    summary.setUserId(param.getUserId());
	    summary.setRegDt(param.getRegDt());
	    summary.setTotalCalories(totalCalories);
	    summary.setTotalDuration(totalDuration);

	    return summary;
	}

}
