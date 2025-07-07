package com.pcwk.ehr.board.service;

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

import com.pcwk.ehr.board.domain.FreeBoardDTO;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FreeBoardMapper;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mapper.NoticeMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class FreeBoardServiceTest {
	Logger log = LogManager.getLogger(getClass());

	
	@Autowired
	ApplicationContext context;

	@Autowired
	FreeBoardService freeboardService;

	@Autowired
	MembershipMapper mMapper;

	@Autowired
	FreeBoardMapper mapper;

	FreeBoardDTO dto01;

	MembershipDTO mDto01;

	SearchDTO search;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌───────────────────────────────┐");
		log.debug("│ setUp()                       │");
		log.debug("└───────────────────────────────┘");

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

		dto01 = new FreeBoardDTO("user01", "제목1", "내용1", 0, null, null);

		search = new SearchDTO();

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌───────────────────────────────┐");
		log.debug("│ tearDown()                    │");
		log.debug("└───────────────────────────────┘");
	}
	@Test
	void doSelectOne() {
		log.debug("┌───────────────────────────────┐");
		log.debug("│ doSelectOne()                 │");
		log.debug("└───────────────────────────────┘");
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		// 3. doselectone() service 호출

		// 1. 전체삭제
		mapper.deleteAll();
		assertEquals(0, mapper.getCount());

		// 2. 단건등록
		log.debug("before:{}", dto01);
		int flag = mapper.doSave(dto01);
		log.debug("after:{}", dto01);
		assertEquals(1, flag);

		// 등록자 수정
		dto01.setUserId("user01");
		// 3
		FreeBoardDTO outVO = freeboardService.doSelectOne(dto01);

		assertNotNull(outVO);
		assertEquals(1, outVO.getViewCount());
	}

	@Test
	void beans() {
		log.debug("┌───────────────────────────────┐");
		log.debug("│ setUp()                       │");
		log.debug("└───────────────────────────────┘");

		assertNotNull(context);
		assertNotNull(freeboardService);
		assertNotNull(mapper);

		log.debug("context:" + context);
		log.debug("freeboardService:" + freeboardService);
		log.debug("mapper:" + mapper);	}

}
