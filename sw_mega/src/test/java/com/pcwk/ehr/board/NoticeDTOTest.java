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

	    // 1.전체삭제
	    mapper.deleteAll();

	 // 1. 데이터 저장
	    dto01.setUserId("user1");
	    dto01.setTitle("공지사항입니다.");
	    dto01.setContent("삭제 테스트용 데이터입니다.");
	    dto01.setViewCount(0);
	    mapper.doSave(dto01);

	    // 2. 검색 조건 설정
	    SearchDTO search = new SearchDTO();
	    search.setSearchDiv(null); // userId 검색
	    search.setSearchWord("user1"); // 저장된 userId와 반드시 일치해야 함

	    // 3. 조회 후 비어 있는지 확인
	    List<NoticeDTO> list = mapper.doRetrieve(search);
	    if (list.isEmpty()) {
	        fail("조회 결과가 없습니다. 삭제할 데이터를 찾을 수 없습니다.");
	        return;
	    }

	    // 4. 삭제 테스트 진행
	    NoticeDTO param = new NoticeDTO();
	    param.setNoCode(list.get(0).getNoCode()); // 삭제할 항목
	    int result = mapper.doDelete(param);
	    assertEquals(1, result, "정상적으로 삭제되지 않았습니다.");

	    
//	    // 2.등록할 공지사항 객체 생성
//	    NoticeDTO dto01 = new NoticeDTO();
//	    dto01.setUserId("user1"); // 💡 검색어로 사용할 값
//	    dto01.setTitle("제목1");
//	    dto01.setContent("내용1");
//	    dto01.setViewCount(0);
//
//	    NoticeDTO dto02 = new NoticeDTO();
//	    dto02.setUserId("user2");
//	    dto02.setTitle("제목2");
//	    dto02.setContent("내용2");
//	    dto02.setViewCount(1);
//
//	    NoticeDTO dto03 = new NoticeDTO();
//	    dto03.setUserId("user3");
//	    dto03.setTitle("제목3");
//	    dto03.setContent("내용3");
//	    dto03.setViewCount(2);
//
//	    // 3. 등록
//	    mapper.doSave(dto01);
//	    mapper.doSave(dto02);
//	    mapper.doSave(dto03);
//
//	    // 4. 등록 건수 확인
//	    int count = mapper.getCount();
//	    assertEquals(3, count);
//
//	    // 5. 검색 조건 설정 (작성자(userId)로 검색)
//	    SearchDTO search = new SearchDTO();
//	    search.setSearchDiv("60");            // 30 = userId 기준 검색
//	    search.setSearchWord("user1");       // 등록한 dto01의 userId 값
//
//	    // 6. 조회
//	    List<NoticeDTO> list = mapper.doRetrieve(search);
//	    if (list.isEmpty()) {
//	        fail("조회 결과가 없습니다. 삭제할 데이터를 찾을 수 없습니다.");
//	        return;
//	    }
//
//	    // 7. 단건 삭제
//	    NoticeDTO param = new NoticeDTO();
//	    param.setNoCode(list.get(0).getNoCode()); // noCode로 삭제
//	    int result = mapper.doDelete(param);
//	    assertEquals(1, result); // 1건 삭제되었는지 확인
//
//	    // 8. 최종 등록 건수 확인
//	    count = mapper.getCount();
//	    assertEquals(2, count);
//	    log.debug("최종 등록 건수: " + count);
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
