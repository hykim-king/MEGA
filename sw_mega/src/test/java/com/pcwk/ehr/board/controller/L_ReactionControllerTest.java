package com.pcwk.ehr.board.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.L_ReactionMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class L_ReactionControllerTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	L_ReactionMapper mapper;

	@Autowired
	MembershipMapper mMapper;

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	L_ReactionDTO dto01;
	L_ReactionDTO dto02;
	L_ReactionDTO dto03;

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

		dto01 = new L_ReactionDTO("user01", "y", "게시판", 8765);

		search = new SearchDTO();

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}

	// @Disabled
	@Test
	void doDelete() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.단건등록
		// 3. 등록 건수 세기
		// 4. 조회
		// 5. 단건삭제 파라미터 설정 및 단건삭제
		// 6. 등록 건수 세기

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
//		mapper.doSave(dto02);
//		mapper.doSave(dto03);

		// 3. 등록 건수 세기
		int count = mapper.getCount();
		assertEquals(1, count);
		log.debug(count);

		// 4. 조회
		search.setSearchWord("8765");
		List<L_ReactionDTO> list = mapper.doRetrieve(search);
		for (L_ReactionDTO vo : list) {
			log.debug("vo: {}", vo);
		}

		// 5. 단건삭제 파라미터 설정 및 단건삭제
		L_ReactionDTO param = new L_ReactionDTO();
		param.setReactionCode(list.get(0).getReactionCode());
		int result = mapper.doDelete(param);
		assertEquals(1, result);
		log.debug("result" + result);

		// 6. 등록 건수 세기
		count = mapper.getCount();
		assertEquals(0, count);
		log.debug(count);

	}

	// @Disabled
	@Test
	void doRetrieve() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doRetrieve()               │");
		log.debug("└────────────────────────────┘");

		// 1. 전체삭제
		// 2. 단건등록
		// 3. 조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
//		mapper.doSave(dto02);
//		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(1, count);
		log.debug(count);

		// 4.
		L_ReactionDTO param = new L_ReactionDTO();
		search.setSearchWord("8765");
		List<L_ReactionDTO> list = mapper.doRetrieve(search);
		for (L_ReactionDTO vo : list) {
			log.debug("vo: {}", vo);
		}
	}

	@Test
	void doSave() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSave()                   │");
		log.debug("└────────────────────────────┘");

		// 1. 전체 삭제
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2. 등록
		int flag = mapper.doSave(dto01);
		assertEquals(1, flag);
		log.debug("등록 flag: {}", flag);

		// 3. 등록된 데이터 조회로 확인
		SearchDTO search = new SearchDTO();
		search.setSearchWord("8765"); // targetKey 기준

		List<L_ReactionDTO> list = mapper.doRetrieve(search);
		assertEquals(1, list.size());

		L_ReactionDTO outVO = list.get(0);
		log.debug("조회된 outVO: {}", outVO);

		// 4. 등록 데이터 일치 여부 확인
		assertEquals(dto01.getUserId(), outVO.getUserId());
		assertEquals(dto01.getReactionType(), outVO.getReactionType());
		assertEquals(dto01.getTargetType(), outVO.getTargetType());
		assertEquals(dto01.getTargetCode(), outVO.getTargetCode());

	}

	// @Disabled
	@Test
	void test() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ test()                     │");
		log.debug("└────────────────────────────┘");
		assertNotNull(mapper);
		assertNotNull(mockMvc);
		assertNotNull(webApplicationContext);

		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mapper" + mapper);
		log.debug("mockMvc:{}", mockMvc);

	}

}
