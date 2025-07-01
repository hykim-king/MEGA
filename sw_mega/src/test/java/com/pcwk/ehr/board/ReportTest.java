package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.board.mapper.ReportMapper;
import com.pcwk.ehr.cmn.SearchDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ReportTest {

	@Autowired
	ReportMapper reportMapper;

	@Autowired
	ReportMapper mapper;

	ReportDTO dto01;
	ReportDTO dto02;
	ReportDTO dto03;

	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");

		dto01 = new ReportDTO("user01", "신고사유", "공지사항", 101);

	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}
	//@Disabled
	@Test
	void doSaveandSelectOne() {
		reportMapper.deleteAll(); // 1. 전체 삭제

		int saveFlag = reportMapper.doSave(dto01); // 2. 저장
		assertEquals(1, saveFlag, "신고 저장 실패!");

		List<ReportDTO> list = reportMapper.doRetrieve(search); // 3. 전체 목록 가져오기
		assertEquals(1, list.size(), "저장된 신고가 1건이 아님");

		ReportDTO saved = list.get(0);
		ReportDTO selected = reportMapper.doSelectOne(saved.getReportCode()); // 4. 단건 조회

		assertNotNull(selected, "단건 조회 결과 없음!");
		assertEquals(dto01.getUserId(), selected.getUserId());
		assertEquals(dto01.getReason(), selected.getReason());
		assertEquals(dto01.getTargetType(), selected.getTargetType());
		assertEquals(dto01.getTargetCode(), selected.getTargetCode());

		log.info("▶ 저장된 신고: {}", selected);
	}

	//@Disabled
	@Test
	void getCount() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ getCount()                 │");
		log.debug("└────────────────────────────┘");

		reportMapper.deleteAll();

		reportMapper.doSave(dto01);
		int count = reportMapper.getCount();

		assertEquals(1, count, "신고 건수 불일치");
	}

	//@Disabled
	@Test
	void doDelete() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");

		reportMapper.deleteAll();

		reportMapper.doSave(dto01);
		List<ReportDTO> list = reportMapper.doRetrieve(search);
		assertEquals(1, list.size());

		int deleteFlag = reportMapper.doDelete(list.get(0));
		assertEquals(1, deleteFlag, "신고 삭제 실패");

		int count = reportMapper.getCount();
		assertEquals(0, count, "신고 삭제 후에도 데이터가 남아 있음");
	}

	//@Disabled
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