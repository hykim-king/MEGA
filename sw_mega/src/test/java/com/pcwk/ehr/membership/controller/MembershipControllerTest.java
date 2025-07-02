package com.pcwk.ehr.membership.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.membership.mapper.MembershipMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "classpath:servlet-context-test.xml"
})
@WebAppConfiguration
@Import(MembershipControllerTest.TestStubConfig.class)
class MembershipControllerTest {

    /* -------------------------------------------------
     * 테스트용 Stub Bean 모음
     * ------------------------------------------------- */
    @Configuration
    static class TestStubConfig {

        /* 가짜 FindIdService */
        @Bean
        public com.pcwk.ehr.login.service.FindIdService findIdService() {
            return Mockito.mock(com.pcwk.ehr.login.service.FindIdService.class);
        }

        /* 가짜 FindPwdService */
        @Bean
        public com.pcwk.ehr.login.service.FindPwdService findPwdService() {
            return Mockito.mock(com.pcwk.ehr.login.service.FindPwdService.class);
        }

        /* 가짜 LoginService  ← 반드시 여기 안에! */
        @Bean
        public com.pcwk.ehr.login.service.LoginService loginService() {
            return Mockito.mock(com.pcwk.ehr.login.service.LoginService.class);
        }
    }   // ★ TestStubConfig 닫는 중괄호 (반드시 필요)

    /* -------------------------------------------------
     * 실제 테스트 필드/메서드
     * ------------------------------------------------- */
    @Autowired WebApplicationContext ctx;
    @Autowired MembershipMapper mapper;

    MockMvc mockMvc;
    MembershipDTO dto;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

        mapper.deleteAll();
        dto = new MembershipDTO("pcwk01", "admin01", "pcwk@naver.com", "pw1234",
                                new Date(System.currentTimeMillis()), "N",
                                "token", 1, null, null, 0);
        mapper.doSave(dto);
    }

    /** 목록 페이지 200 OK & 뷰 이름 */
    
    //@Disabled
    @Test
    void listPage() throws Exception {
        mockMvc.perform(get("/membership/doRetrieve.do")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .param("searchWord", ""))
               .andExpect(status().isOk())
               .andExpect(view().name("membership/membership_list"))
               .andExpect(model().attributeExists("list"));
    }

    /** 상세 페이지 200 OK & 모델 */
    @Disabled
    @Test
    void detailPage() throws Exception {
        mockMvc.perform(get("/membership/doSelectOne.do")
                        .param("userId", "pcwk01"))
               .andExpect(status().isOk())
               .andExpect(view().name("membership/membership_detail"))
               .andExpect(model().attributeExists("member"));
    }

    /** 등록 후 리다이렉트 */
    @Disabled
    @Test
    void addMember() throws Exception {
        mockMvc.perform(post("/membership/doSave.do")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userId", "test02")
                        .param("email", "test02@naver.com")
                        .param("password", "pw5678")
                        .param("birth", "1990-01-01")
                        .param("grade", "1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/membership/doRetrieve.do"));
    }
}  
