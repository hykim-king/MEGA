package com.pcwk.ehr.membership.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.pcwk.ehr.membership.service.MembershipService;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "classpath:servlet-context-test.xml"
})
@Import(MembershipControllerTest.TestStubConfig.class)
class MembershipControllerTest {

    /*-------------------------------------------------
     * 테스트용 Stub Bean 모음
     *-------------------------------------------------*/
    @Configuration
    static class TestStubConfig {
        // 가짜 LoginService (로그인 관련 컨트롤러가 있다면 필요)
        @Bean
        public com.pcwk.ehr.login.service.LoginService loginService() {
            return Mockito.mock(com.pcwk.ehr.login.service.LoginService.class);
        }

        // 가짜 MembershipService (이번 테스트 대상)
        @Bean
        public MembershipService membershipService() {
            return Mockito.mock(MembershipService.class);
        }
    }

    /*-------------------------------------------------
     * 실제 테스트 필드/메서드
     *-------------------------------------------------*/
    @Autowired
    WebApplicationContext ctx;

    @Autowired
    MembershipMapper mapper;

    @Autowired
    @Qualifier("membershipService")
    MembershipService service; // Stub 주입

    MockMvc mockMvc;
    MembershipDTO dto;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

        mapper.deleteAll();
        dto = new MembershipDTO(
                "pcwk01", "admin01", "pcwk@naver.com", "pw1234",
                new Date(System.currentTimeMillis()),
                "N", "token", 1, null, null  // 10개 파라미터 맞춤
        );
    }

    /** 등록 후 리다이렉트 테스트 */
    @Test
    void addMember() throws Exception {
        // 가짜 서비스가 1 리턴하도록 세팅
        Mockito.when(service.save(Mockito.any(MembershipDTO.class))).thenReturn(1);
        
        String expectedMsg = java.net.URLEncoder.encode("회원가입 성공", "UTF-8");
        String expectedUrl = "/membership/doSaveView.do?success=true&msg=" + expectedMsg;

        
        
        mockMvc.perform(post("/membership/doSave.do")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userId", "test02")
                .param("email", "test02@naver.com")
                .param("password", "pw5678")
                .param("birth", "1990-01-01")
                .param("grade", "1")
                .param("emailAuth", "N")
                .param("emailAuthToken", "token123"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(expectedUrl));
    }
}
