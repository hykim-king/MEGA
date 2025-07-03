package com.pcwk.ehr.membership.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;


@Service                // ① Service 빈으로 등록
@Transactional          // (선택) 트랜잭션 전파
public class MembershipServiceImpl implements MembershipService {

    @Autowired          // ② MyBatis 매퍼 주입
   MembershipMapper membershipMapper;

    /*───────────────────────────────────────────*/
    /* 1. 회원 등록                               */
    /*───────────────────────────────────────────*/
    @Override
    public int save(MembershipDTO dto) throws SQLException {
        return membershipMapper.doSave(dto);
    }

    /*───────────────────────────────────────────*/
    /* 2. 목록 조회                               */
    /*───────────────────────────────────────────*/
    @Override
    public List<MembershipDTO> retrieve(SearchDTO search) throws SQLException {
        return membershipMapper.doRetrieve(search);
    }

    /*───────────────────────────────────────────*/
    /* 3. 단건 조회                               */
    /*───────────────────────────────────────────*/
    @Override
    public MembershipDTO selectOne(MembershipDTO dto) throws SQLException {
        return membershipMapper.doSelectOne(dto);
    }

    /*───────────────────────────────────────────*/
    /* 4. 회원 수정                               */
    /*───────────────────────────────────────────*/
    @Override
    public int update(MembershipDTO dto) {
        return membershipMapper.doUpdate(dto);
    }

    /*───────────────────────────────────────────*/
    /* 5. 회원 삭제                               */
    /*───────────────────────────────────────────*/
    @Override
    public int delete(MembershipDTO dto) {
        return membershipMapper.doDelete(dto);
    }
    
    /*───────────────────────────────────────────*/
    /* 6. 등록건수                                                                  */
    /*───────────────────────────────────────────*/
    @Override
    public int getCount() throws SQLException { 
    	return membershipMapper.getCount(); } 
    
    /*───────────────────────────────────────────*/
    /* 7. 아이디 체크                                                              */
    /*───────────────────────────────────────────*/
    
    @Override
    public boolean isUserIdAvailable(String userId) throws SQLException {                    
    	return membershipMapper.idCheck(userId) == 0;
    }
    

    
//    /*───────────────────────────────────────────*/
//    /* 8. 등급 업그레이드                                                           */
//    /*───────────────────────────────────────────*/
//    @Override
//    public void upgradeLevels() throws SQLException {
//        List<MembershipDTO> list = membershipMapper.getAll();
//        for (MembershipDTO m : list) {
//            boolean changed = false;
//
//           // BASIC → SILVER
//           if (m.getGrade() == 1 && m.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER) {
//               m.setGrade(2);
//               changed = true;
//           }
//           // SILVER → GOLD
//           if (m.getGrade() == 2 && m.getRecommend() >= MIN_RECOMMEND_FOR_GOLD) {
//               m.setGrade(3);
//               changed = true;
//           }
//           if (changed) {
//               membershipMapper.doUpdate(m);
//           }
//      }
//    }
//

}
