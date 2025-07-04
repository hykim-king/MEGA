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

	/**
	 * 사용자가 입력한 값을 Exercise 테이블에서 가져온 기준에 적용하여 총 소모 칼로리 계산하여 출력
	 */
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
	
	/**
	 * 목록조회된  전체 값의 총 소모 칼로리 및 운동시간 출력 
	 */
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
