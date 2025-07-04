package com.pcwk.ehr.mypage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;


@Service
public class ExerciseServiceImpl implements ExerciseService {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ExerciseMapper mapper;
	

	@Override
	public int doDelete(ExerciseDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(ExerciseDTO param) {
		return mapper.doUpdate(param);
	}
	
	@Override
	public int doSave(ExerciseDTO param) {
		return mapper.doSave(param);
	}
	
	@Override
	public List<ExerciseDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);
	}

	/**
	 * 사용자가 입력한 값을 기준 데이터에 적용하여 소모 칼로리 계산하여 출력 
	 */
	@Override
	public ExerciseDTO doSelectOne(ExerciseDTO param) {
	    
	    // 1. DB에서 운동 기준 정보 가져오기 (운동명으로 조회)
	    ExerciseDTO dbData = mapper.doSelectOne(param);

	    String type = dbData.getExerciseType(); // "유산소" or "근력"
	    int totalCal = 0;

	    if ("유산소".equals(type)) {
	        // 유산소 운동: 시간 기반 계산
	        totalCal = dbData.getCalBurn() * param.getDuration();

	        dbData.setDuration(param.getDuration()); // 사용자가 입력한 운동 시간 저장

	    } else if ("근력".equals(type)) {
	        // 근력 운동: 반복, 세트, 무게 기반 계산
	        totalCal = dbData.getCalBurn()
	                    * param.getSetCount()
	                    * param.getRepsPerSet()
	                    * param.getStrenthWeight();

	        dbData.setSetCount(param.getSetCount());
	        dbData.setRepsPerSet(param.getRepsPerSet());
	        dbData.setStrenthWeight(param.getStrenthWeight());
	    }

	    dbData.setTotalCal(totalCal);

	    return dbData;
	}


}
