package com.pcwk.ehr.membership.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@Mapper
public interface MembershipMapper extends WorkDiv<MembershipDTO>{
	
    /** ID 중복 체크: 0이면 사용 가능 */
    int idCheck(String userId) throws SQLException;

    /** 등급 업그레이드용 전체 조회 */
    List<MembershipDTO> getAll() throws SQLException;

    /** 목록 조회(페이징) - WorkDiv에 없다면 선언 */
    List<MembershipDTO> doRetrieve(SearchDTO search);
	
    /**
	 * 전체삭제
	 */
	void deleteAll();
	
	/**
	 * 등록 건수 세기
	 * @return
	 */
	int getCount();
	
	  /*
		 * 다건등록
		 * @return 1(성공)/0(실패)
		 */
		int saveAll();
	
	
}
