package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

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

import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodDiaryDaoTest {
	
	@Autowired 
	FoodDiaryMapper fdMapper;
	
	@Autowired
	FoodMapper fMapper;
	
	@Autowired
	MembershipMapper mMapper;
	

	FoodDiaryDTO dto01;
	FoodDiaryDTO dto02;
	FoodDiaryDTO dto03;
	
	FoodDTO fDto01;
	
	MembershipDTO mDto01;
	
	FoodDiaryOutDTO diaryOut;
	
	@Autowired
	ApplicationContext context;
	
	Logger log = LogManager.getLogger(getClass());
	

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
		
		dto01 = new FoodDiaryDTO("user01", "김치볶음밥", 150, "아침", "2025-05-26");
		dto02 = new FoodDiaryDTO("user01", "김치볶음밥", 50, "점심", "2025-05-24");
	    dto03 = new FoodDiaryDTO("user01", "김치볶음밥", 150, "저녁", "2025-05-26");
	    
	}
	

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
	}
	
	@Test
	void doDelete() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doDelete()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 전체조회 및 항목선택
		//4. 삭제
		//5. 등록건수 조회
		
		//1. 
		fdMapper.deleteAll();
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		fdMapper.doSave(dto03);
		
		int count = fdMapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//다건조회 파라미터 설정
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) fdMapper.doRetrieve(param);
		for(FoodDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
		
		//다건조회 목록 중 삭제할 항목 선택
		FoodDiaryOutDTO inVO = list.get(0);
		log.debug("inVO: {}", inVO);
		
		//삭제 파라미터 설정
		FoodDiaryDTO paramDe = new FoodDiaryDTO();
		paramDe.setFdCode(inVO.getFdCode());
		
		//4. 
		int result = fdMapper.doDelete(paramDe);
		assertEquals(1, result);
		log.debug(result);
		
		//5. 
		count = fdMapper.getCount();
		assertEquals(2, count);
		log.debug("count: {}", count);
		
	}
	
	//@Disabled
	@Test
	void doUpdate() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doUpdate()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 전체조회 및 항목선택
		//4. 수정
		
		//1. 
		fdMapper.deleteAll();
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		fdMapper.doSave(dto03);
		
		int count = fdMapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//다건조회 파라미터 설정
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) fdMapper.doRetrieve(param);
		for(FoodDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
		
		//다건조회 목록 중 수정할 항목 선택
		FoodDiaryOutDTO inVO = list.get(0);
		log.debug("inVO: {}", inVO);
		
		//업데이트 파라미터 설정
		FoodDiaryDTO paramUp = new FoodDiaryDTO();
		paramUp.setFdCode(inVO.getFdCode());
		paramUp.setFoodName(inVO.getFoodName());
		paramUp.setGrams(200);
		paramUp.setMealType("점심");
		paramUp.setRegDt(inVO.getRegDt());
		paramUp.setUserId(inVO.getUserId());
		
		//4. 
		int result = fdMapper.doUpdate(paramUp);
		
		assertEquals(1, result);
		log.debug("result: {}", result);
		
	}
	
	//@Disabled
	@Test
	void doRetrieve() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                                            │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 다건등록
		//3. 전체조회
		
		//1.
		fdMapper.deleteAll();
		
		//2. 
		int count = fdMapper.saveAll();
		log.debug("count: {}", count);
		
		assertEquals(502, count);
		
		//3. 
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("user01");
		param.setRegDt("2025-06-25");
		
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) fdMapper.doRetrieve(param);
		for(FoodDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
	}
	

	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(fdMapper);
		assertNotNull(fMapper);
		assertNotNull(mMapper);
		
		log.debug(context);
		log.debug(fdMapper);
		log.debug(fMapper);
		log.debug(mMapper);
		
	}

}
