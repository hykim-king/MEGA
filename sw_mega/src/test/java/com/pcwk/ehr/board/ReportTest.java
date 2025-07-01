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

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.board.mapper.ReportMapper;
import com.pcwk.ehr.cmn.SearchDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ReportTest {

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

		dto01 = new ReportDTO("user1", "신고사유", "공지사항", 0001);
		dto02 = new ReportDTO("user2", "신고사유", "자유게시판", 0002);
		dto03 = new ReportDTO("user3", "신고사유", "자유게시판", 0003);

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
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 3.
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug(count);

		// 4.
		ReportDTO param = new ReportDTO();
		search.setSearchWord("0001");
		List<ReportDTO> list = mapper.doRetrieve(search);
		for (ReportDTO vo : list) {
			log.debug("vo: {}", vo);
		}
	}

	//@Disabled
	@Test
	void beans() {

		assertNotNull(context);
		assertNotNull(mapper);

		log.debug(context);
		log.debug(mapper);

	}
}