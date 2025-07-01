package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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

import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.board.mapper.NoticeMapper;
import com.pcwk.ehr.cmn.SearchDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

class NoticeDTOTest {
	@Autowired
	NoticeMapper mapper;

	NoticeDTO dto01;
	NoticeDTO dto02;
	NoticeDTO dto03;

	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setup() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");

		dto01 = new NoticeDTO("user1", "제목1", "내용1", 0, null, null);
		dto02 = new NoticeDTO("user2", "제목2", "내용2", 1, null, null);
		dto03 = new NoticeDTO("user3", "제목3", "내용3", 2, null, null);

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

	 // 매번 동일한 결과가 도출 되도록 작성
	 		// 1. 전체삭제
	 		// 2. 단건등록
	 		// 2.1 등록건수 비교
	 		// 3. 삭제
	 		// 4. 등록건수 비교==0

	 		// 1.
	 		mapper.deleteAll();

	 		// 2.
	 		int flag = mapper.doSave(dto01);
	 		log.debug("flag:{}", flag);
	 		assertEquals(1, flag);

	 		// 2.1
	 		int count = mapper.getCount();
	 		log.debug("count:{}", count);
	 		assertEquals(1, count);

	 		// 3 삭제
	 		flag = mapper.doDelete(dto01);
	 		assertEquals(0, flag);

	 		// 4.건수비교
	 		count = mapper.getCount();
	 		log.debug("count:{}", count);
	 		assertEquals(1, count);
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
		NoticeDTO param = new NoticeDTO();
		search.setSearchWord("user1");
		List<NoticeDTO> list = mapper.doRetrieve(search);
		for (NoticeDTO vo : list) {
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
