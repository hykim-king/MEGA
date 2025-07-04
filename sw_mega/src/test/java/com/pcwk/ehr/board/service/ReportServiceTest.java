package com.pcwk.ehr.board.service;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mapper.ReportMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class ReportServiceTest {

	@Autowired
	ReportMapper mapper;

	@Autowired
	MembershipMapper mMapper;

	ReportDTO dto01;
	ReportDTO dto02;
	ReportDTO dto03;

	MembershipDTO mDto01;

	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

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

		dto01 = new ReportDTO("user01", "신고사유", "공지사항", 101);
	}
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}
	

	// @Disabled
	@Test
	void doSaveandSelectOne() {
		mapper.deleteAll(); // 1. 전체 삭제

		int saveFlag = mapper.doSave(dto01); // 2. 저장
		assertEquals(1, saveFlag, "신고 저장 실패!");

		List<ReportDTO> list = mapper.doRetrieve(search); // 3. 전체 목록 가져오기
		assertEquals(1, list.size(), "저장된 신고가 1건이 아님");

		ReportDTO saved = list.get(0);
		ReportDTO selected = mapper.doSelectOne(saved.getReportCode()); // 4. 단건 조회

		assertNotNull(selected, "단건 조회 결과 없음!");
		assertEquals(dto01.getUserId(), selected.getUserId());
		assertEquals(dto01.getReason(), selected.getReason());
		assertEquals(dto01.getTargetType(), selected.getTargetType());
		assertEquals(dto01.getTargetCode(), selected.getTargetCode());

		log.info("▶ 저장된 신고: {}", selected);
	}

	// @Disabled
	@Test
	void getCount() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ getCount()                 │");
		log.debug("└────────────────────────────┘");

		mapper.deleteAll();

		mapper.doSave(dto01);
		int count = mapper.getCount();

		assertEquals(1, count, "신고 건수 불일치");
	}

	// @Disabled
	@Test
	void doDelete() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");

		mapper.deleteAll();

		mapper.doSave(dto01);
		List<ReportDTO> list = mapper.doRetrieve(search);
		assertEquals(1, list.size());

		int deleteFlag = mapper.doDelete(list.get(0));
		assertEquals(1, deleteFlag, "신고 삭제 실패");

		int count = mapper.getCount();
		assertEquals(0, count, "신고 삭제 후에도 데이터가 남아 있음");
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

		// 3.
		int count = mapper.getCount();
		assertEquals(1, count);
		log.debug(count);

		// 4.
		SearchDTO search = new SearchDTO();
		search.setSearchWord("101");

		List<ReportDTO> list = mapper.doRetrieve(search);
		for (ReportDTO vo : list) {
			log.debug("vo: {}", vo);
		}
	}

	

	// @Disabled
	@Test
	void beans() {

		assertNotNull(context);
		assertNotNull(mapper);

		log.debug(context);
		log.debug(mapper);
	}

}
