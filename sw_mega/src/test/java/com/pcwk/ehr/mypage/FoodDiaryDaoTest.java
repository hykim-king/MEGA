package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.*;

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

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mypage.mapper.FoodMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodDiaryDaoTest {
	
	@Autowired 
	FoodDiaryMapper mapper;
	
	FoodDiaryDTO dto01;
	FoodDiaryDTO dto02;
	FoodDiaryDTO dto03;
	
	SearchDTO search;
	
	FoodDiaryOutDTO diaryOut;
	
	@Autowired
	ApplicationContext context;
	
	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new FoodDiaryDTO("yangsh", "김치찌개", 150, "아침", "2025-05-26");
		dto02 = new FoodDiaryDTO("yangsi", "스크럼블", 50, "점심", "2025-05-24");
	    dto03 = new FoodDiaryDTO("yangsr", "차돌박이", 150, "저녁", "2025-05-26");
	    
	    search = new SearchDTO();
	    
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
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//다건조회 파라미터 설정
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("yangsh");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) mapper.doRetrieve(param);
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
		int result = mapper.doDelete(paramDe);
		assertEquals(1, result);
		log.debug(result);
		
		
	}
	
	@Disabled
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
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);
		
		//다건조회 파라미터 설정
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("yangsh");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) mapper.doRetrieve(param);
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
		int result = mapper.doUpdate(paramUp);
		
		assertEquals(1, result);
		log.debug("result: {}", result);
		
	}
	
	@Disabled
	@Test
	void doRetrieve() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                                            │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 다건등록
		//3. 전체조회
		
		//1.
		mapper.deleteAll();
		
		//2. 
		int count = mapper.saveAll();
		log.debug("count: {}", count);
		
		assertEquals(502, count);
		
		//3. 
		FoodDiaryDTO param = new FoodDiaryDTO();
		param.setUserId("yangsh179");
		param.setRegDt("2025-06-25");
		
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) mapper.doRetrieve(param);
		for(FoodDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
	}
	

	@Test
	void beans() {
		assertNotNull(context);
		
		log.debug(context);
		
	}

}
