package com.pcwk.ehr.membership.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.membership.DTO.MembershipDTO;

@Mapper
public interface MembershipMapper extends WorkDiv<MembershipDTO>{

}
