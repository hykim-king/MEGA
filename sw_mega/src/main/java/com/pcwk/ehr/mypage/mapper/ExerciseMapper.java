package com.pcwk.ehr.mypage.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;

@Mapper
public interface ExerciseMapper extends WorkDiv<ExerciseDTO>{
	
}
