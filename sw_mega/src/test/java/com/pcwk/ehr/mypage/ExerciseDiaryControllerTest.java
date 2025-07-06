package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.mapper.ExerciseDiaryMapper;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.ExerciseSummaryDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class ExerciseDiaryControllerTest {
	
Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//웹브라우저 대역 객체
	MockMvc mockMvc;
	
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
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
		
		dto01 = new ExerciseDiaryDTO("user01", eCode, null, 40, 35, 5, 10, "2025-05-25");
		dto02 = new ExerciseDiaryDTO("user01", eCode, null, 20, 20, 4, 10, "2025-05-25");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ tearDown()                            │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@Test
	void doDelete() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doDelete()                            │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건저장
		//3. 단건삭제 
		
		//1.
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());

		//2.
		int flag = edMapper.doSave(dto01);
		assertEquals(1, flag);

		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.post("/exerciseDiary/doDelete.do")
				 	.param("edCode", String.valueOf(dto01.getEdCode()));   

		ResultActions resultActions = mockMvc.perform(requestBuilder)
		    .andExpect(status().isOk())
		    .andExpect(
		        MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		//2.3 호출 결과 받기
		String returnBody = resultActions.andDo(print())
		    .andReturn().getResponse().getContentAsString();

		log.debug("returnBody: {}", returnBody);
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage.toString());

		assertEquals(1, resultMessage.getMessageId());
		assertEquals("삭제 되었습니다.", resultMessage.getMessage());
		
	}
	
	
	@Disabled
	@Test
	void doUpdate() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doUpdate()                            │");
		log.debug("└───────────────────────────────────────┘");
		//1. 전체삭제
		//2. 단건등록
		//3. 수정
		
		//1. 
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());
		
		//2.
		int flag = edMapper.doSave(dto01);
		assertEquals(1, flag);
		
		
		int upInt = 99;
		//3.1 url호출, method:post, param                     
		MockHttpServletRequestBuilder requestBuilder        
		 =MockMvcRequestBuilders.post("/exerciseDiary/doUpdate.do")   
		 	.param("edCode", String.valueOf(dto01.getEdCode()))               
			.param("strenthWeight", String.valueOf(dto01.getStrenthWeight()+upInt))   
			.param("duration", String.valueOf(dto01.getDuration()))   
			.param("setCount", String.valueOf(dto01.getSetCount()))   
			.param("repsPerSet", String.valueOf(dto01.getRepsPerSet()+upInt))         
			;                                               
		
		//3.2 호출
		ResultActions resultActions =  mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
				
		//3.3 호출 결과 받기
		String returnBody = resultActions.andDo(print())
				.andReturn().getResponse().getContentAsString();
				
		log.debug("returnBody: {}", returnBody);
		
		//json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("수정 되었습니다.", resultMessage.getMessage());
		
		
	}
	
	
	@Disabled
	@Test
	void doSave() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSave()                              │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 등록
		//3. 상태값 비교
		
		//1.
		edMapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder requestBuilder
		 =MockMvcRequestBuilders.post("/exerciseDiary/doSave.do")
			.param("userId", dto01.getUserId())
			.param("eCode", String.valueOf(dto01.geteCode()))
			.param("strenthWeight", String.valueOf(dto01.getStrenthWeight()))
			.param("duration", String.valueOf(dto01.getDuration()))
			.param("setCount", String.valueOf(dto01.getSetCount()))
			.param("repsPerSet", String.valueOf(dto01.getRepsPerSet()))
			.param("regDt", dto01.getRegDt())
			;
		
		//2.2 호출
		ResultActions resultActions =  mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
		
		//2.3 호출 결과 받기
		String returnBody = resultActions.andDo(print())
						    .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}", returnBody);
				
		//json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("운동 일지가 등록되었습니다.", resultMessage.getMessage());
		
	}
		
	
	
	@Disabled
	@Test
	void getDailySummary() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ getDailySummary()                     │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 당일 총 영양정보 조회
		//3.3 viewName 비교 
		
		//1. 
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());
		
		//2. 
		edMapper.doSave(dto01);
		edMapper.doSave(dto02);
		
		int count = edMapper.getCount();
		assertEquals(2, count);
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/exerciseDiary/getDailySummary.do")
		    	.param("userId", "user01")
		    	.param("regDt", "2025-05-25");
		    	
		    
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		ExerciseSummaryDTO vo = (ExerciseSummaryDTO) model.get("vo");
		log.debug("vo: {}", vo);
		
		//3.3
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName: {}", viewName);
		
		assertEquals("/exerciseDiary/exerciseDiary_list", viewName);
		
		
	}
	
	
	@Disabled
	@Test
	void doRetrieve() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doRetrieve()                          │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 목록 조회
		//4. 비교
		
		//1. 
		edMapper.deleteAll();
		assertEquals(0, edMapper.getCount());
		
		//2. 
		edMapper.doSave(dto01);
		edMapper.doSave(dto02);
		
		int count = edMapper.getCount();
		assertEquals(2, count);
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/exerciseDiary/doRetrieve.do")
		    	.param("userId", "user01")
		    	.param("regDt", "2025-05-25");
		    	
		    
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		
		List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) model.get("list");
		for(ExerciseDiaryOutDTO vo : list) {
			log.debug(vo);
		}
		
		assertEquals(2, list.size());
		
		
	}
	

	//@Test
	void beans() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ beans()                               │");
		log.debug("└───────────────────────────────────────┘");
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(edMapper);
		assertNotNull(eMapper);
		assertNotNull(mMapper);
		
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("edMapper:{}", edMapper);
		log.debug("eMapper:{}", eMapper);
		log.debug("mMapper:{}", mMapper);
	}

}
