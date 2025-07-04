package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

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
import org.springframework.transaction.PlatformTransactionManager;

import com.pcwk.ehr.login.domain.LoginDTO;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.mapper.LoginMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class LoginServiceTest {
	
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	MembershipMapper membershipMapper;
	    
	MembershipDTO dto01;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;

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
	
	@Test
	void login() throws SQLException{
		log.debug("┌──────────────────────────┐");
		log.debug("│ login()*                 │");
		log.debug("└──────────────────────────┘");
		
		membershipMapper.doDelete(dto01);
		membershipMapper.doSave(dto01);
		
		
		LoginDTO dto = new LoginDTO();
		dto.setUserId("subtang");
	    dto.setPassword("tangSub!23");
	    
	    LoginDTO login =  login(dto);
	    log.debug("login : {}", login);
	    log.debug("로그인 성공! {}님이 로그인 하셨습니다.", dto.getUserId());
	}
	
	//
	private LoginDTO login(LoginDTO dto) {
        LoginDTO result = loginService.doSelectOne(dto);
        if (result != null) {
            return result;
        } else {
            log.debug("해당 정보의 사용자가 존재하지 않습니다. 아이디와 비밀번호를 다시 확인해주세요");
            return null;
        }
    }
	
	@Disabled
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(loginService);
		assertNotNull(membershipMapper);
		assertNotNull(dataSource);
		assertNotNull(transactionManager);
		
		log.debug("context:{}",context);
		log.debug("loginService:{}",loginService);
		log.debug("membershipMapper:{}",membershipMapper);
		log.debug("dataSource:{}",dataSource);
		log.debug("transactionManager:{}",transactionManager);
	}

}
