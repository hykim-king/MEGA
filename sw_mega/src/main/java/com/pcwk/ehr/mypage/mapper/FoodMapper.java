/**
 * Package Name : com.pcwk.ehr.mapper <br/>
 * Class Name: FoodMapper.java <br/>
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

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodInputDTO;
import com.pcwk.ehr.mypage.domain.SummaryNutritionDTO;


@Mapper
public interface FoodMapper extends  WorkDiv<FoodDTO>{
	
	//숫자카운트
	int getCount() throws SQLException;
	
}
