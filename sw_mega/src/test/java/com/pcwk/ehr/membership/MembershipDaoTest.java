package com.pcwk.ehr.membership;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.DTO.MembershipDTO;
import com.pcwk.ehr.membership.mapper.MembershipMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class MembershipDaoTest {

	@Autowired
	MembershipMapper mapper;
	
	MembershipDTO dto01;
	MembershipDTO dto02;
	MembershipDTO dto03;
	
	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new MembershipDTO( "userA01", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png");
		dto02 = new MembershipDTO( "userA02", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png");
		dto03 = new MembershipDTO( "userA03", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png");
		
		search = new SearchDTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void doSelectOne() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 다건등록
		//3. 단건조회
		
		//1.
		mapper.deleteAll();
		
//		mapper.doSave(dto01);
//		mapper.doSave(dto02);
//		mapper.doSave(dto03);
		
		//2. 
		int count = mapper.saveAll();
		log.debug("count" + count);

		assertEquals(200, count);
		
		
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(mapper);
		
		log.debug(context);
		log.debug("mapper:"+mapper);
	}
	

}
