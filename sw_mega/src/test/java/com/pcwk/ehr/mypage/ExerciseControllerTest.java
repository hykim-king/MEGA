package com.pcwk.ehr.mypage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class ExerciseControllerTest {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//웹브라우저 대역 객체
	MockMvc mockMvc;
	
	@Autowired
	ExerciseMapper mapper;
	
	ExerciseDTO dto01;
	
	SearchDTO search;
	

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ setUp()                               │");
		log.debug("└───────────────────────────────────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		dto01 = new ExerciseDTO("달리기", "유산소", null, "남성", 70, 150);
		
		search = new SearchDTO();
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
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		//2.
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);

		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.post("/exercise/doDelete.do")
		    		.param("eCode", String.valueOf(dto01.geteCode()));  

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
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		//2.
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		
		
		String upString = "_U";
		int upInt = 99;
		//3.1 url호출, method:post, param                     
		MockHttpServletRequestBuilder requestBuilder        
		 =MockMvcRequestBuilders.post("/exercise/doUpdate.do")   
		 	.param("eCode", String.valueOf(dto01.geteCode()))               
			.param("exerciseName", dto01.getExerciseName()+upString)                   
			.param("exerciseType", "근력")         
			.param("gender", "여성")     
			.param("weight", String.valueOf(dto01.getWeight()+upInt)) 
			.param("calBurn", String.valueOf(dto01.getCalBurn()+upInt))  
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
		mapper.deleteAll();
		
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder requestBuilder
		 =MockMvcRequestBuilders.post("/exercise/doSave.do")
		 	.param("exerciseName", dto01.getExerciseName())
			.param("exerciseType", dto01.getExerciseType())
			.param("gender", dto01.getGender())
			.param("weight", String.valueOf(dto01.getWeight()))
			.param("calBurn", String.valueOf(dto01.getCalBurn()))
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
		assertEquals("달리기 등록되었습니다.", resultMessage.getMessage());
		
	}
	
	
	@Disabled
	@Test
	void doRetrieve() throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doRetrieve()                          │");
		log.debug("└───────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 다건등록(saveAll)
		//3. 목록 조회
		//4. 비교
		
		//1. 
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());
		
		//2. 
		int count = mapper.saveAll();
		assertEquals(502, mapper.getCount());
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/exercise/doRetrieve.do")
		    	.param("pageNo", "1")
		    	.param("pageSize", "10")
		    	.param("searchDiv", "20")
		    	.param("searchWord", "가슴");
		    	
		    
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		int totalCnt = (int) model.get("totalCnt");
		log.debug("totalCnt:{}", totalCnt);
		
		List<ExerciseDTO> list = (List<ExerciseDTO>) model.get("list");
		for(ExerciseDTO vo : list) {
			log.debug(vo);
		}
		
		assertEquals(10, list.size());
	}
	
	
	@Disabled
	@Test
	void doSelectOne () throws Exception{
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSelectOne()                         │");
		log.debug("└───────────────────────────────────────┘");
		//1. 전체삭제
		//2. 등록
		//3. 한건조회
		//4. 수정
		
		//1. 
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		//2.
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		
		//3.
		MockHttpServletRequestBuilder requestBuilder 
		    = MockMvcRequestBuilders.get("/exercise/doSelectOne.do")
		      .param("eCode", String.valueOf(dto01.geteCode()))
			  .param("duration", String.valueOf(45));
		
		//3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder)
			    .andExpect(status().isOk());
		
		//3.2 
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		ExerciseDTO vo = (ExerciseDTO) model.get("vo");
		log.debug("vo: {}", vo);
		
		//3.3
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName: {}", viewName);
		
		assertEquals("/exercise/exercise_mod", viewName);
		
		
	}

	@Test
	void beans() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ beans()                               │");
		log.debug("└───────────────────────────────────────┘");
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(mapper);
		
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("mapper:{}", mapper);
	}

}
