package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

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

import com.pcwk.ehr.mapper.ExerciseDiaryMapper;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
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
	
	ExerciseDiaryDTO dto01;
	ExerciseDiaryDTO dto02;
	
	ExerciseDTO eDto01;
	
	MembershipDTO mDto01;
	
	ExerciseDiaryOutDTO diaryOut;
	
	int eCode;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		eDto01 = new ExerciseDTO("달리기", "유산소", null, "남성", 70, 100);
		
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
		
		dto01 = new ExerciseDiaryDTO("user01", eCode, 70, null, 35, 5, 10, "2025-05-25");
		dto02 = new ExerciseDiaryDTO("user01", eCode, 70, null, 40, 5, 13, "2025-05-25");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void doRetrieve() {
		//1. 전체삭제
		//2. 단건등록
		//3. doRetrieve() Service 호출
		
		
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
