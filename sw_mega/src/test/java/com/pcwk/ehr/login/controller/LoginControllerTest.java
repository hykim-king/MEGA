package com.pcwk.ehr.login.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.login.domain.LoginDTO;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.mapper.LoginMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class LoginControllerTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	 @Autowired
	 WebApplicationContext webApplicationContext;
	 
	 @Autowired
	 LoginService loginService;

	 @Autowired
	 MembershipMapper membershipMapper;

	 MockMvc mockMvc;
	 MembershipDTO dto01;
	 
	 @Autowired
	 LoginMapper loginMapper;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new MembershipDTO( "subtang", "tangSub","tangSub@test.com", "tangSub!23", Date.valueOf("1999-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-07-08"));
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		membershipMapper.doDelete(dto01);
		membershipMapper.doSave(dto01);

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	//@Disabled
    @Test
    void testLoginSuccessAndFail() throws Exception {
        Object[][] cases = {
            // userId,    password,      expectedView,      expectedAttrName, expectedAttrValue
            {"subtang", "tangSub!23",   "loginSuccess",    "loginResult",    true}   // 성공
            //{"subtang", "wrongPwd",     "login",           "msg",            "일치하는 정보가 없습니다. 다시 입력해주세요."}, // 실패
            //{"wrongId", "tangSub!23",   "login",           "msg",            "일치하는 정보가 없습니다. 다시 입력해주세요."} // 실패
        };

        for (Object[] c : cases) {
            String userId = (String)c[0];
            String password = (String)c[1];
            String expectedView = (String)c[2];
            String expectedAttrName = (String)c[3];
            Object expectedAttrValue = c[4];

            log.debug("==== 테스트 케이스 시작 ====");
            log.debug("userId: {}, password: {}", userId, password);
            log.debug("expectedView: {}, expectedAttrName: {}, expectedAttrValue: {}",
                expectedView, expectedAttrName, expectedAttrValue);

            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/login/loginView.do")
                        .param("userId", userId)
                        .param("password", password))
                .andExpect(status().isOk());
               // .andExpect(view().name(expectedView))
                //.andExpect(model().attribute(expectedAttrName, expectedAttrValue));

            resultActions.andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print());

            MvcResult mvcResult = resultActions.andReturn();
            Object modelValue = mvcResult.getModelAndView().getModel().get(expectedAttrName);
            log.debug("실제 model 반환값: {}", modelValue);
            log.debug("==== 테스트 케이스 종료 ====");
        }
    }


	@Disabled
	@Test
	void beans() {
        assertNotNull(webApplicationContext);
        assertNotNull(loginService);
        assertNotNull(membershipMapper);
        assertNotNull(loginMapper);
        log.debug("webApplicationContext:{}"+webApplicationContext);
        log.debug("loginService:{}"+loginService);
        log.debug("membershipMapper:{}"+membershipMapper);
        log.debug("loginMapper:{}"+loginMapper);
	}

}
