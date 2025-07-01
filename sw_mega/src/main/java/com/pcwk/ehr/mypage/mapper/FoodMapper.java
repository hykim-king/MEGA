package com.pcwk.ehr.mypage.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.FoodDTO;

@Mapper
public interface FoodMapper extends WorkDiv<FoodDTO> {

	
}
