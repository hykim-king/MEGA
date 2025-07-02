package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.login.domain.FindIdDTO;
import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.domain.LoginDTO;
import com.pcwk.ehr.login.mapper.LoginMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.membership.mapper.MembershipMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class LoginDaoTest {
	Logger log=LogManager.getLogger(getClass());

    @Autowired
    LoginMapper loginMapper;
    
    @Autowired
    MembershipMapper membershipMapper;
    
    MembershipDTO dto01;
    
    @Autowired
	ApplicationContext context;
    
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new MembershipDTO( "subtang", "tangSub","tangSub@test.com", "tangSub!23", Date.valueOf("1999-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	//@Disabled
	@Test
	void doSelectOne() throws SQLException{
		membershipMapper.doDelete(dto01);
		membershipMapper.doSave(dto01);
		
		LoginDTO dto = new LoginDTO();
		dto.setUserId("subtang");
	    dto.setPassword("tangSub!23");
	    LoginDTO login =  loginMapper.doSelectOne(dto);
	    log.debug("로그인: {}", login);
	}

    @Test
    void beans() {
        assertNotNull(context);
        log.debug("context:{}"+context);
    }
}
