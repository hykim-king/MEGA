package com.pcwk.ehr.membership.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.membership.mapper.MembershipMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class MembershipServiceTest {

    @Autowired
    @Qualifier("membershipServiceImpl")  //스프링이 어떤 Bean을 주입해야 할지 모호할 때
    MembershipService service;

    @Autowired
    MembershipMapper mapper;

    MembershipDTO dto;

    @BeforeEach
    void setUp() throws SQLException {
        mapper.deleteAll(); // 전체 삭제
        dto = new MembershipDTO(
            "pcwk01", "admin01", "pcwk01@naver.com", "pw1234",
            new Date(System.currentTimeMillis()), "N", "authToken",
            1, null, null, 0);
    }

    @Test
    void testSaveAndSelectOne() throws SQLException {
        assertTrue(service.isUserIdAvailable(dto.getUserId()));

        int saveResult = service.save(dto);
        assertEquals(1, saveResult);

        MembershipDTO result = service.selectOne(dto);
        assertEquals(dto.getUserId(), result.getUserId());
    }

    @Test
    void testRetrieve() throws SQLException {
        service.save(dto);

        SearchDTO search = new SearchDTO(); 
        search.setPageSize(10);   // 꼭 1 이상
        search.setPageNo(1);      // 꼭 1 이상
        search.setSearchWord(""); // 검색어 없으면 빈 문자열
        
        List<MembershipDTO> list = service.retrieve(search);

        assertEquals(1, list.size());
        assertEquals("pcwk01", list.get(0).getUserId());
    }

    @Test
    void testIsUserIdAvailable() throws SQLException {
        assertTrue(service.isUserIdAvailable("newuser"));

        service.save(dto);

        assertFalse(service.isUserIdAvailable("pcwk01"));
    }
}
