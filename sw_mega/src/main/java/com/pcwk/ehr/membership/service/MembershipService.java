package com.pcwk.ehr.membership.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.DTO.MembershipDTO;

public interface MembershipService {

    // BASIC -> SILVER 로그인횟수 기준
    int MIN_LOGIN_COUNT_FOR_SILVER = 50;

    // SILVER -> GOLD 추천횟수 기준
    int MIN_RECOMMEND_FOR_GOLD = 30;

    /**
     * 회원 등록
     */
    int save(MembershipDTO dto) throws SQLException;

    /**
     * 회원 목록 조회
     */
    List<MembershipDTO> retrieve(SearchDTO search);

    /**
     * 단건 조회
     */
    MembershipDTO selectOne(MembershipDTO dto) throws SQLException;

    /**
     * 회원 수정
     */
    int update(MembershipDTO dto);

    /**
     * 회원 삭제
     */
    int delete(MembershipDTO dto);

    /**
     * 회원 등급 업그레이드
     */
    void upgradeLevels() throws SQLException;
} 
