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

import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;
import com.pcwk.ehr.mypage.service.FoodDiaryService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodDiaryServiceTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	FoodDiaryService foodDiaryService;
	
	@Autowired
	MembershipMapper mMapper;
	
	@Autowired
	FoodMapper fMapper;
	
	@Autowired
	FoodDiaryMapper fdMapper;
	
	FoodDTO fDto01;
	MembershipDTO mDto01;
	
	FoodDiaryDTO dto01;
	FoodDiaryDTO dto02;

	NutritionSummaryDTO nutrition;
	
	FoodDiaryOutDTO fdOut;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		fDto01 = new FoodDTO("김치볶음밥", 200, 430, 30, 20, 20, 30);
		
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
		
		//!!Food 데이터 관리 !!
		//1. Food 전체삭제
		fMapper.deleteAll();
		//2. Food 단건주입
		fMapper.doSave(fDto01);
		
		
		dto01 = new FoodDiaryDTO("user01", "김치볶음밥", 150, "아침", "2025-05-24");
		dto02 = new FoodDiaryDTO("user01", "김치볶음밥", 50, "점심", "2025-05-24");
		
		nutrition = new NutritionSummaryDTO();
		
		fdOut = new FoodDiaryOutDTO();
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
		//3. 총 영양정보 조회
		
		//1. 
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		log.debug("dto01:{}", dto01);
		log.debug("dto02:{}", dto02);
		
		//2.1 
		int count = fdMapper.getCount();
		assertEquals(2, count);
		log.debug("count: {}", count);
		
		//3. 
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-24");
		NutritionSummaryDTO result = foodDiaryService.getDailySummary(param);
		
		assertNotNull(result);
		log.debug("result: {}", result);
		
	}
	
	//@Disabled
	@Test
	void doRetrieve() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		//1. 전체삭제
		//2. 단건등록
		//3. doSelectone() Service 호출
		
		//1. 
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		log.debug("dto01:{}", dto01);
		log.debug("dto02:{}", dto02);
		
		//3. 
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-24");
		List<FoodDiaryOutDTO> outVO = (List<FoodDiaryOutDTO>) foodDiaryService.doRetrieve(param);
				
		assertNotNull(outVO);
		log.debug("outVO: {}", outVO);
		
		
	}

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(foodDiaryService);
		assertNotNull(fdMapper);
		assertNotNull(fMapper);
		assertNotNull(mMapper);
		
		log.debug(context);
		log.debug(foodDiaryService);
		log.debug(fdMapper);
		log.debug(fMapper);
		log.debug(mMapper);
	}

}
