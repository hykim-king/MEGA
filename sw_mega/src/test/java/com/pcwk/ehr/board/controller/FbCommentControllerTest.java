package com.pcwk.ehr.board.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.board.domain.FreeBoardDTO;
import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FbCommentMapper;
import com.pcwk.ehr.mapper.FreeBoardMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mapper.NoCommentMapper;
import com.pcwk.ehr.mapper.NoticeMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class FbCommentControllerTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	FbCommentMapper mapper;

	@Autowired
	MembershipMapper mMapper;

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	FreeBoardCommentDTO dto01;
	FreeBoardCommentDTO dto02;
	FreeBoardCommentDTO dto03;

	MembershipDTO mDto01;

	@Autowired
	FreeBoardMapper nMapper;

	SearchDTO search;

	FreeBoardDTO nDto01;

	int fbCode;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");
		
		nDto01 = new FreeBoardDTO("user01", "제목1", "내용1", 0, null, null);

		mDto01 = new MembershipDTO("user01", "admin01", "yangsh5972@naver.com", "4321as@",
				java.sql.Date.valueOf("1992-05-12"), "Y", "tokA1234XYZ", 2, "profileA.png",
				java.sql.Date.valueOf("2025-05-12"));

		// !!membership 데이터 관리 !!
		// 1. membership 전체삭제
		mMapper.deleteAll();
		// 2. membership 데이터 단건 주입
		mMapper.doSave(mDto01);
		// 3. membership 데이터 단건 조회
		MembershipDTO mParam = new MembershipDTO();
		mParam.setUserId("user02");
		MembershipDTO mResult = mMapper.doSelectOne(mParam);
		log.debug("mResult: {}", mResult);

		// !!Notice 데이터 관리 !!
		// 1. Notice 전체 삭제
		nMapper.deleteAll();
		// 2. Notice 단건 등록
		nMapper.doSave(nDto01);
		fbCode = nDto01.getFbCode();
		log.debug("noCode: {}", fbCode);

		dto01 = new FreeBoardCommentDTO(fbCode, "user1", "내용1");
		dto02 = new FreeBoardCommentDTO(fbCode, "user2", "내용2");
		dto03 = new FreeBoardCommentDTO(fbCode, "user3", "내용3");

		search = new SearchDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}
	
	//@Disabled
	@Test
	void doDelete() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.등록
		// 3.전체조회
		// 4.단건삭제

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count:{}", count);

		// 4.
		search.setPageSize(10);
		search.setPageNo(1);
		search.setSearchWord(String.valueOf(dto01.getFbCode()));
		List<FreeBoardCommentDTO> list = (List<FreeBoardCommentDTO>) mapper.doRetrieve(search);
		for (FreeBoardCommentDTO doVO : list) {
			log.debug("doVO:{}", doVO);
		}
		// 4.2단건삭제
		int result = mapper.doDelete(list.get(0));
		log.debug(result);

		//
		count = mapper.getCount();
		assertEquals(2, count);
		log.debug("count:{}", count);
	}
	
	@Disabled
	@Test
	void doUpdate() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doUpdate()                 │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.등록
		// 3.조회
		// 4.수정파라미터 설정
		// 5.수정

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count:{}", count);

		// 4.
		search.setSearchWord(String.valueOf(dto01.getFbCode()));
		search.setPageSize(10);
		search.setPageNo(1);
		List<FreeBoardCommentDTO> list = (List<FreeBoardCommentDTO>) mapper.doRetrieve(search);
		for (FreeBoardCommentDTO doVO : list) {
			log.debug("doVO:{}", doVO);
		}

		// . 수정 파라미터 설정
		list.get(1).setContent("어쩌구저쩌구");
		int result = mapper.doUpdate(list.get(1));
		log.debug(result);

	}
	
	@Disabled
	@Test
	void doSelectOne() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.단건등록
		// 3.등록건수 조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count:{}", count);

		// 4.
		search.setSearchWord(String.valueOf(dto01.getFbCode()));
		search.setPageSize(10);
		search.setPageNo(1);
		List<FreeBoardCommentDTO> list = (List<FreeBoardCommentDTO>) mapper.doRetrieve(search);
		for (FreeBoardCommentDTO doVO : list) {
			log.debug("doVO:{}", doVO);
		}

		// 단건 조회 파라미터 설정
		FreeBoardCommentDTO param = new FreeBoardCommentDTO();
		param.setCommentedCode(list.get(0).getCommentedCode());
		log.debug("param:{}", param.getCommentedCode());
	}
	
	
	@Disabled
	@Test
	void doRetrieve() throws SQLException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doRetrieve()               │");
		log.debug("└────────────────────────────┘");

		// 1.전체삭제
		// 2.단건등록
		// 3.등록건수 조회
		// 4.전체조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count:{}", count);

		// 4.
		search.setSearchWord(String.valueOf(dto01.getFbCode()));
		search.setPageSize(10);
		search.setPageNo(1);
		List<FreeBoardCommentDTO> list = (List<FreeBoardCommentDTO>) mapper.doRetrieve(search);
		for (FreeBoardCommentDTO doVO : list) {
			log.debug("doVO:{}", doVO);
		}

	}

	@Disabled
	@Test
	void beans() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ beans()                    │");
		log.debug("└────────────────────────────┘");

		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		assertNotNull(mapper);
	}

}
