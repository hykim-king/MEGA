package com.pcwk.ehr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.DTO.MembershipDTO;
import com.pcwk.ehr.mymapper.domain.MyMapperDTO;   // ← doHello용 DTO

/**
 * MEMBERSHIP 테이블 Mapper (DAO 생략 버전)
 */
@Mapper
public interface MembershipMapper {

    /* === 테스트용 Echo === */
    MyMapperDTO        doHello(MyMapperDTO dto);     // ★ 추가

    /* === CRUD === */
    List<MembershipDTO> doRetrieve(SearchDTO dto);
    MembershipDTO       doSelectOne(MembershipDTO dto);
    int                 doSave(MembershipDTO dto);
    int                 doUpdate(MembershipDTO dto);
    int                 doDelete(MembershipDTO dto);
    int					deleteAllCascade();

    /* === 유틸 / 테스트 === */
    int saveAll();
    int deleteAll();
    int getCount();

	List<MembershipDTO> getAll();
}
