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
import com.pcwk.ehr.mapper.FoodDiaryMapper;
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodDiaryControllerTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//웹브라우저 대역 객체
	MockMvc mockMvc;
	
	@Autowired
	FoodDiaryMapper fdMapper;
	
	@Autowired
	FoodMapper fMapper;
	
	@Autowired
	MembershipMapper mMapper;
	
	
	FoodDiaryDTO dto01;
	FoodDiaryDTO dto02;
	
	FoodDTO fDto01;
	
	MembershipDTO mDto01;
	
	FoodDiaryOutDTO diaryOut;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ setUp()                               │");
		log.debug("└───────────────────────────────────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ tearDown()                            │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	
	//@Test
	void doDelete() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doDelete()                            │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건저장
		//3. 단건삭제 
		
		//1.
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());

		//2.
		int flag = fdMapper.doSave(dto01);
		assertEquals(1, flag);

		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.post("/foodDiary/doDelete.do")
				 	.param("fdCode", String.valueOf(dto01.getFdCode()));    

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
	
	
	//@Disabled
	@Test
	void doUpdate() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doUpdate()                            │");
		log.debug("└───────────────────────────────────────┘");
		//1. 전체삭제
		//2. 단건등록
		//3. 수정
		
		//1. 
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());
		
		//2.
		int flag = fdMapper.doSave(dto01);
		assertEquals(1, flag);
		
		
		int upInt = 99;
		//3.1 url호출, method:post, param                     
		MockHttpServletRequestBuilder requestBuilder        
		 =MockMvcRequestBuilders.post("/foodDiary/doUpdate.do")   
		 	.param("fdCode", String.valueOf(dto01.getFdCode()))               
			.param("grams", String.valueOf(dto01.getGrams()+upInt))                   
			.param("mealType", "저녁")         
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
		fdMapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder requestBuilder
		 =MockMvcRequestBuilders.post("/foodDiary/doSave.do")
		 	.param("userId", dto01.getUserId())
			.param("foodName", dto01.getFoodName())
			.param("grams", String.valueOf(dto01.getGrams()))
			.param("mealType", dto01.getMealType())
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
		assertEquals("음식 일지가 등록되었습니다.", resultMessage.getMessage());
		
	}
	
	
	//@Disabled
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
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		
		int count = fdMapper.getCount();
		assertEquals(2, count);
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/foodDiary/getDailySummary.do")
		    	.param("userId", "user01")
		    	.param("regDt", "2025-05-24");
		    	
		    
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		NutritionSummaryDTO vo = (NutritionSummaryDTO) model.get("vo");
		log.debug("vo: {}", vo);
		
		//3.3
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName: {}", viewName);
		
		assertEquals("/foodDiary/foodDiary_list", viewName);
		
		
	}
	
	//@Disabled
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
		fdMapper.deleteAll();
		assertEquals(0, fdMapper.getCount());
		
		//2. 
		fdMapper.doSave(dto01);
		fdMapper.doSave(dto02);
		
		int count = fdMapper.getCount();
		assertEquals(2, count);
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/foodDiary/doRetrieve.do")
		    	.param("userId", "user01")
		    	.param("regDt", "2025-05-24");
		    	
		    
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		
		List<FoodDiaryOutDTO> list = (List<FoodDiaryOutDTO>) model.get("list");
		for(FoodDiaryOutDTO vo : list) {
			log.debug(vo);
		}
		
		assertEquals(1, list.size());
		
		
	}

	//@Test
	void beans() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ beans()                               │");
		log.debug("└───────────────────────────────────────┘");
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(fdMapper);
		assertNotNull(fMapper);
		assertNotNull(mMapper);
		
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("fdMapper:{}", fdMapper);
		log.debug("fMapper:{}", fMapper);
		log.debug("mMapper:{}", mMapper);
		
	}

}
