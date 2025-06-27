/**
 * Package Name : com.pcwk.ehr.mapper <br/>
 * Class Name: ExerciseDiaryMapper.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-25<br/>
 *
 * ------------------------------------------<br/>
 * @author :PC
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.SummaryCalorieTimeDTO;

@Mapper
public interface ExerciseDiaryMapper extends WorkDiv<ExerciseDTO>{
	
}
