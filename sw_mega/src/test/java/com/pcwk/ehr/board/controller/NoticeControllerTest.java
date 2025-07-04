package com.pcwk.ehr.board.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.pcwk.ehr.mapper.NoticeMapper;

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

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	NoticeDTO dto01;

	SearchDTO search;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		dto01 = new NoticeDTO("user01", "제목1", "내용1", 0, null, null);

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
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
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/doDelete.do").param("NoCode",
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

	@Disabled
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

	@Test
	void beans() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ beans()                    │");
		log.debug("└────────────────────────────┘");

		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(mapper);
		assertNotNull(dto01);

		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("mapper:{}", mapper);
		log.debug("userMapper:{}", dto01);

	}

}
