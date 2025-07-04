package com.pcwk.ehr.board.controller;

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
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mapper.NoticeMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class NoticeControllerTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	NoticeMapper mapper;

	@Autowired
	MembershipMapper mMapper;

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	NoticeDTO dto01;
	NoticeDTO dto02;
	NoticeDTO dto03;

	MembershipDTO mDto01;

	SearchDTO search;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");

		mDto01 = new MembershipDTO("user01", "admin01", "yangsh5972@naver.com", "4321as@", Date.valueOf("1992-05-12"),
				"Y", "tokA1234XYZ", 2, "profileA.png", Date.valueOf("2025-05-12"));
		
		// !!membership 데이터 관리 !!
		// 1. membership 전체삭제
		mMapper.deleteAll();
		// 2. membership 데이터 단건 주입
		mMapper.doSave(mDto01);
		// 3. membership 데이터 단건 조회
		MembershipDTO mParam = new MembershipDTO();
		mParam.setUserId("user01");
		MembershipDTO mResult = mMapper.doSelectOne(mParam);
		log.debug("mResult: {}", mResult);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		dto01 = new NoticeDTO("user01", "제목1", "내용1", 0, null, null);
		dto02 = new NoticeDTO("user01", "제목2", "내용2", 1, null, null);
		dto03 = new NoticeDTO("user01", "제목3", "내용3", 2, null, null);

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}

	//@Disabled
	@Test
	void doRetrieve() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");

		// 1.전체삭제
		// 2.다건등록
		// 3.목록 조회
		// 4.비교

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
	    mapper.doSave(dto01);
	    mapper.doSave(dto02);
	    mapper.doSave(dto03);


		// 3.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/doRetrieve.do")
				.param("pageNo", "1")
				.param("pageSize", "10")
				.param("searchDiv", "20")
				.param("searchWord", "내용");

		// 3.1
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());

		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		int totalCnt = (int) model.get("totalCnt");
		log.debug("totalCnt:{}", totalCnt);

		List<NoticeDTO> list = (List<NoticeDTO>) model.get("list");
		for (NoticeDTO vo : list) {
			log.debug("vo:{}",vo);
		}

		assertEquals(3, list.size());
	}

	//@Disabled
	@Test
	void doUpdate() throws Exception {
		// 1.전체삭제
		// 2.등록
		// 3.한건조회
		// 4.수정

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		log.debug("before:{}", dto01);
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		log.debug("after:{}", dto01);

		// 3.
		NoticeDTO outVO = mapper.doSelectOne(dto01);
		assertNotNull(outVO);
		log.debug("outVO:{}", outVO);

		String upString = "_U";
		int upInt = 99;
		// 4.1 url호출, method:post, param
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/doUpdate.do")
				.param("title", outVO.getTitle() + upString).param("content", outVO.getContent() + upString)
				.param("userId", outVO.getUserId() + upString).param("seq", String.valueOf(outVO.getNoCode()));
		;
		// 4.2 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		// 4.3 호출 결과 받기
		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody: {}", returnBody);
		// 4.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage.toString());

		assertEquals(0, resultMessage.getMessageId());
		assertEquals("수정 실패.", resultMessage.getMessage());

	}

	//@Disabled
	@Test
	void doSelectOne() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");
		// 1.전체삭제
		// 2.등록
		// 3.단건조회

		// 1.
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2.
		log.debug("before:{}", dto01);
		int flag = mapper.doSave(dto01);// 한건 등록
		assertEquals(1, flag);
		log.debug("after:{}", dto01);

		// 3
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/doSelectOne.do")
				.param("noCode", String.valueOf(dto01.getNoCode()));
				//.param("userId", String.valueOf(dto01.getUserId()));

		// 3.1호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());

		// 3.2
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		NoticeDTO outVO = (NoticeDTO) model.get("vo");
		log.debug("outVO:{}", outVO);

		// 조회count 증가
		dto01.setViewCount(dto01.getViewCount() + 1);
		isSameNotice(outVO, dto01);

		// 3.3
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}", viewName);
		assertEquals("board/board_mod", viewName);

	}

	private void isSameNotice(NoticeDTO outVO, NoticeDTO dto01) {
		log.debug("┌──────────────────────────┐");
		log.debug("│ isSameNotice()           │");
		log.debug("└──────────────────────────┘");
		assertEquals(outVO.getNoCode(), dto01.getNoCode());
		assertEquals(outVO.getTitle(), dto01.getTitle());
		assertEquals(outVO.getContent(), dto01.getContent());
		assertEquals(outVO.getViewCount(), dto01.getViewCount());

		assertEquals(outVO.getUserId(), dto01.getUserId());

	}

	//@Disabled
	@Test
	void doDelete() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");
		// 1.전체삭제
		// 2.등록
		// 3.삭제

		// 1.
		mapper.deleteAll();

		// 2.
		log.debug("before:{}", dto01);
		int flag = mapper.doSave(dto01);// 한건 등록
		assertEquals(1, flag);
		log.debug("after:{}", dto01);

		// 3.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/doDelete.do").param("noCode",
				String.valueOf(dto01.getNoCode()));

		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		// 2.3
		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage);

		assertEquals(1, resultMessage.getMessageId());
		assertEquals("삭제 되었습니다.", resultMessage.getMessage());
	}

	//@Disabled
	@Test
	void doSave() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSave()                   │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.등록
		// 3.상태값 비교

		// 1.
		mapper.deleteAll();

		// 2.1 url호출, method:post, param
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/doSave.do")
				.param("title", dto01.getTitle()).param("content", dto01.getContent())
				.param("userId", dto01.getUserId());

		// 2.2
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		// 3.3 호출 결과 받기
		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);

		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}", resultMessage);

		assertEquals(1, resultMessage.getMessageId());
		assertEquals("제목1글이 등록 되었습니다.", resultMessage.getMessage());

	}

	@Disabled
	@Test
	void beans() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ beans()                    │");
		log.debug("└────────────────────────────┘");

		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(mapper);

		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("mapper:{}", mapper);

	}

}
