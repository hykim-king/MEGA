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

import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.service.FindPwdService;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class FindPwdServiceTest {
	
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	FindPwdService findPwdService;
	
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
		
		dto01 = new MembershipDTO("subtang","tangSub","tangSub@test.com", "tangSub!23", Date.valueOf("1999-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void findPwd() throws SQLException{
		log.debug("┌──────────────────────────┐");
		log.debug("│ findPwd()*               │");
		log.debug("└──────────────────────────┘");
		
		membershipMapper.doDelete(dto01);
		membershipMapper.doSave(dto01);
		assertEquals(2, membershipMapper.getCount());
		
		FindPwdDTO dto = new FindPwdDTO();
		dto.setUserId("subtang");
	    dto.setEmail("tangSub@test.com");
	    
	    String password = checkPwd(dto);
	    log.debug("조회된 password: {}", password);
	}
	
	// email 및 userId를 받아서 해당 email 및 userId이 DB에 존재하는지 확인하고 존재하면 해당email 및 userId의 password를 리턴받아 출력
	// boolean으로 true/false를 하여 확인할것인지, 아니면 바로 값만 받아서 출력할것인지 결정
	
	private String checkPwd(FindPwdDTO dto) {
		String password=findPwdService.findPwd(dto);
		if(password != null) {
			return password;
		}else {
			log.debug("가입한 사용자가 없습니다, 이메일 및 아이디를 다시한번 확인하여 주세요");
			return null;
		}
	}

	@Disabled
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(findPwdService);
		assertNotNull(membershipMapper);
		assertNotNull(dataSource);
		assertNotNull(transactionManager);
		
		log.debug("context:{}",context);
		log.debug("findPwdService:{}",findPwdService);
		log.debug("membershipMapper:{}",membershipMapper);
		log.debug("dataSource:{}",dataSource);
		log.debug("transactionManager:{}",transactionManager);
	}

}
