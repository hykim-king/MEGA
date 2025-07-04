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

import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.service.ExerciseService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class ExerciseServiceTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	ExerciseMapper mapper;
	
	ExerciseDTO dto01;
	ExerciseDTO dto02;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new ExerciseDTO("달리기", "유산소", null, "남성", 70, 150, 45);
		dto02 = new ExerciseDTO("벤치프레스", "근력", "가슴", null, null, 100, 30, 5, 10);
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
		//2. 단건등록 x 2 및 등록 건수 세기
		//3. doSelectone() Service 호출 x 2
		
		//1. 
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		//2. 
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		int count = mapper.getCount();
		assertEquals(2, count);
		
		//3. 
		ExerciseDTO outVO = exerciseService.doSelectOne(dto01);
		ExerciseDTO outVO2 = exerciseService.doSelectOne(dto02);
		
		assertNotNull(outVO);
		assertNotNull(outVO2);
		log.debug("outVO: {}", outVO);
		log.debug("outVO2: {}", outVO2);
		
		
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(context);
		assertNotNull(context);
		
		log.debug("context:"+context);
		log.debug("exerciseService:"+exerciseService);
		log.debug("mapper:"+mapper);
		
	}

}
