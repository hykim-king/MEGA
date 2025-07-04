package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.*;

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

import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.service.FoodService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodServiceTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodMapper mapper;
	
	FoodDTO dto01;
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new FoodDTO("김치볶음밥", 200, 430, 30, 20, 20, 30, 100);
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void doSelectOne() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		//1. 전체삭제
		//2. 단건등록
		//3. doSelectone() Service 호출
		
		//1. 
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
				
		//2. 
		int flag = mapper.doSave(dto01);
		log.debug("dto01:{}", dto01);
		assertEquals(1, flag);
		
		//3. 
		FoodDTO outVO = foodService.doSelectOne(dto01);
		
		assertNotNull(outVO);
		log.debug("outVO: {}", outVO);
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(foodService);
		assertNotNull(mapper);
		
		log.debug("context:"+context);
		log.debug("foodService:"+foodService);
		log.debug("mapper:"+mapper);
	}

}
