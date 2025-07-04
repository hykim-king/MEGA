package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

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

import com.pcwk.ehr.mapper.ExerciseDiaryMapper;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.ExerciseSummaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.service.ExerciseDiaryService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class ExerciseDiaryServiceTest {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	ExerciseDiaryService exerciseDiaryService;
	
	@Autowired
	ExerciseDiaryMapper edMapper;
	
	@Autowired 
	ExerciseMapper eMapper;
	
	@Autowired
	MembershipMapper mMapper;
	
	ExerciseDTO eDto01;
	MembershipDTO mDto01;
	
	ExerciseDiaryDTO dto01;
	ExerciseDiaryDTO dto02;
	
	ExerciseSummaryDTO summary;
	
	ExerciseDiaryOutDTO edOut;
	
	int eCode;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		eDto01 = new ExerciseDTO("벤치프레스", "근력", "가슴", null, null, 100);
		
		mDto01 = new MembershipDTO("user01", "admin01", "yangsh5972@naver.com", "4321as@", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
		
		//!!membership 데이터 관리 !!
		//1. membership 전체삭제
		mMapper.deleteAll();
		//2. membership 데이터 단건 주입
		mMapper.doSave(mDto01);
		//3. membership 데이터 단건 조회
		MembershipDTO mParam=new MembershipDTO();
		mParam.setUserId("user01");
		MembershipDTO mResult = mMapper.doSelectOne(mParam);
		log.debug("mResult: {}", mResult);
		
		//!!exercise 데이터 관리 !!
		//1. exercise 전체삭제
		eMapper.deleteAll();
		//2. exercise 데이터 단건 주입
		eMapper.doSave(eDto01);
		eCode = eDto01.geteCode();
		log.debug("eCode:{}", eCode);
		
		dto01 = new ExerciseDiaryDTO("user01", eCode, null, 25, 35, 3, 10, "2025-05-25");
		dto02 = new ExerciseDiaryDTO("user01", eCode, null, 25, 40, 5, 5, "2025-05-25");
	
		summary = new ExerciseSummaryDTO();
		
		edOut = new ExerciseDiaryOutDTO();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void getDailySummary() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ getDailySummary()                                       │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		//1. 전체삭제 
		//2. 단건등록
		//3. 총 운동시간 및 소모 칼로리 조회 
		
		//1. 
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());
		
		//2. 
		edMapper.doSave(dto01);
		edMapper.doSave(dto02);
		int count = edMapper.getCount();
		assertEquals(2, count);
		
		//3. 
		ExerciseDiaryDTO param = new ExerciseDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-25");
		ExerciseSummaryDTO result = exerciseDiaryService.getDailySummary(param);
		
		assertNotNull(result);
		log.debug("result: {}", result);
		
	}
	
	//@Disabled
	@Test
	void doRetrieve() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                                            │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		//1. 전체삭제
		//2. 단건등록
		//3. doRetrieve() Service 호출
		
		//1. 
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());
		
		//2. 
		edMapper.doSave(dto01);
		edMapper.doSave(dto02);
		int count = edMapper.getCount();
		assertEquals(2, count);
		
		//3.
		ExerciseDiaryDTO param = new ExerciseDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-25");
		List<ExerciseDiaryOutDTO> outVO = (List<ExerciseDiaryOutDTO>) exerciseDiaryService.doRetrieve(param);
		
		assertNotNull(outVO);
		log.debug("outVO: {}", outVO);
		
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(exerciseDiaryService);
		assertNotNull(edMapper);
		assertNotNull(eMapper);
		assertNotNull(mMapper);
		
		log.debug("context: {}", context);
		log.debug("exerciseDiaryService: {}", exerciseDiaryService);
		log.debug("edMapper: {}", edMapper);
		log.debug("eMapper: {}", eMapper);
		log.debug("mMapper: {}", mMapper);
	}

}
