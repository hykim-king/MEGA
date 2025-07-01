package com.pcwk.ehr.membership;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.pcwk.ehr.membership.domain.MembershipDTO;
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
		
		dto01 = new MembershipDTO( "userA01", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
		dto02 = new MembershipDTO( "userA02", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
		dto03 = new MembershipDTO( "userA03", "adminA01","userA01@test.com", "pwA!23", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
		
		search = new SearchDTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void doDelete() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doDelete()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//2.1 삭제 파라미터 설정
		//3. 삭제
		//4. 등록건수 조회

		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		
		//등록건수 조회 
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//2.1 
		MembershipDTO param=new MembershipDTO();
		param.setUserId(dto02.getUserId());
		
		//3.
		int result = mapper.doDelete(param);
		assertEquals(1, result);
		log.debug("result:{}", result);
		
		//4. 등록건수 조회
		count = mapper.getCount();
		assertEquals(2, count);
		log.debug("count: {}", count);
		
	}
	
	//@Disabled
	@Test
	void doUpdate() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doUpdate()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록 건수 조회
		//3. 수정
		
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		
		//2.1 
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//수정 파라미터 값 설정
		MembershipDTO param=new MembershipDTO();
		param.setUserId(dto01.getUserId());
		param.setPassword("46378");
		param.setGrade(dto01.getGrade());
		param.setProfileImage(dto01.getProfileImage());
		
		//3. 수정
		int result = mapper.doUpdate(param);
		assertEquals(1, result);
		log.debug("result:{}", result);
	
		
	}
	
	
	//@Disabled
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
		
		
		//2. 
		int count = mapper.saveAll();
		log.debug("count" + count);

		assertEquals(200, count);
		
		//3.
		MembershipDTO param=new MembershipDTO();
		param.setUserId("user2");
		MembershipDTO result =  mapper.doSelectOne(param);
		log.debug("result: {}", result);
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(mapper);
		
		log.debug(context);
		log.debug("mapper:"+mapper);
	}
	

}
