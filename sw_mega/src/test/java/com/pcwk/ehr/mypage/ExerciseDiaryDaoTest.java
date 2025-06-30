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

import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.mapper.ExerciseDiaryMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class ExerciseDiaryDaoTest {

	@Autowired
	ExerciseDiaryMapper mapper;
	
	ExerciseDiaryDTO dto01;
	ExerciseDiaryDTO dto02;
	ExerciseDiaryDTO dto03;
	
	ExerciseDiaryOutDTO diaryOut;
	
	
	@Autowired
	ApplicationContext context;
	
	Logger log = LogManager.getLogger(getClass());

	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new ExerciseDiaryDTO("yangsh", "1005", 70, null, 35, 5, 10, "2025-05-26");
		dto02 = new ExerciseDiaryDTO("yangsh", "1005", 70, null, 35, 5, 10, "2025-05-25");
		dto03 = new ExerciseDiaryDTO("yangsh", "1005", 70, null, 35, 5, 10, "2025-05-26");
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Test
	void  doDelete() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doDelete()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 전체조회 및 항목선택
		//4. 삭제
		//5. 등록건수 조회
		
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
		ExerciseDiaryDTO param = new ExerciseDiaryDTO();
		param.setUserId("yangsh");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) mapper.doRetrieve(param);
		for(ExerciseDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
		
		//다건조회 목록 중 삭제할 항목 선택
		ExerciseDiaryOutDTO inVO = list.get(0);
		log.debug("inVO: {}", inVO);
		
		//삭제 파라미터 설정
		ExerciseDiaryDTO paramDe = new ExerciseDiaryDTO();
		paramDe.setEdCode(inVO.getEdCode());
		
		//4. 
		int result = mapper.doDelete(paramDe);
		assertEquals(1, result);
		log.debug(result);
		
		//5. 
		count = mapper.getCount();
		assertEquals(2, count);
		log.debug("count: {}", count);
		
	}
	
	@Disabled
	@Test
	void doUpdate() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doUpdate()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록 x 3
		//3. 전체조회
		//4. 수정하기
		
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
		ExerciseDiaryDTO param = new ExerciseDiaryDTO();
		param.setUserId("yangsh");
		param.setRegDt("2025-05-26");
		
		//3. 
		List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) mapper.doRetrieve(param);
		for(ExerciseDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
		
		//다건조회 목록 중 수정할 항목 선택
		ExerciseDiaryOutDTO inVO = list.get(0);
		log.debug("inVO: {}", inVO);
		
		//업데이트 파라미터 설정
		ExerciseDiaryDTO paramUp = new ExerciseDiaryDTO();
		paramUp.setEdCode(inVO.getEdCode());
		paramUp.setCardioWeight(inVO.getCardioWeight());
		paramUp.setStrenthWeight(inVO.getStrenthWeight());
		paramUp.setDuration(45);
		paramUp.setSetCount(inVO.getSetCount());
		paramUp.setRepsPerSet(inVO.getRepsPerSet());
		
		//4. 
		int result = mapper.doUpdate(paramUp);
		
		assertEquals(1, result);
		log.debug("result: {}", result);
		
	}
	
	@Disabled
	@Test
	void doRetrieve() throws Exception{
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
		ExerciseDiaryDTO param = new ExerciseDiaryDTO();
		param.setUserId("yangsh");
		param.setRegDt("2025-05-26");
		
		List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) mapper.doRetrieve(param);
		for(ExerciseDiaryOutDTO vo : list) {
			log.debug("vo: {}", vo);	
		}
	}
	
	@Test
	void beans() {
		assertNotNull(context);
		
		log.debug(context);
		
	}

}
