package com.pcwk.ehr.board;

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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.mapper.NoticeMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

class NoticeDaoTest {
	@Autowired
	NoticeMapper mapper;
	
	@Autowired
	MembershipMapper mMapper;

	NoticeDTO dto01;
	NoticeDTO dto02;
	NoticeDTO dto03;
	
	MembershipDTO mDto01;

	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setup() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");
		
		mDto01 = new MembershipDTO("user01", "admin01", "yangsh5972@naver.com", "4321as@", Date.valueOf("1992-05-12"),"Y", "tokA1234XYZ", 2, "profileA.png",Date.valueOf("2025-05-12"));
		
		//!!membership 데이터 관리 !!
		//1. membership 전체삭제
		mMapper.deleteAll();
		//2. membership 데이터 단건 주입
		mMapper.doSave(mDto01);
		//3. membership 데이터 단건 조회
		MembershipDTO mParam=new MembershipDTO();
		mParam.setUserId("user01");
		MembershipDTO mResult = mMapper.doSelectOne(mParam);
		log.debug("mResult: {}", mResult);

		dto01 = new NoticeDTO("user01", "제목1", "내용1", 0, null, null);
		dto02 = new NoticeDTO("user01", "제목2", "내용2", 1, null, null);
		dto03 = new NoticeDTO("user01", "제목3", "내용3", 2, null, null);

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
	void viewCount() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ viewCount()                │");
		log.debug("└────────────────────────────┘");
		// 전체삭제
		// 단건등록
		// 전체조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);

		int count = mapper.getCount();
		assertEquals(1, count);
		log.debug("count:{}", count);

		// paging
		search.setSearchWord(dto01.getTitle());
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNo(1);

		// 3.
		List<NoticeDTO> list = mapper.doRetrieve(search);
		for (NoticeDTO vo : list) {
			log.debug("vo: {}", vo);
		}

		// 항목 선택
		int result = mapper.viewCount(list.get(0));
		log.debug(result);

		// 조회
		NoticeDTO result1 = mapper.doSelectOne(list.get(0));
		log.debug("result1:{}" ,result1);


	}

	//@Disabled
	@Test
	void doSelectOne() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");

		// 1. 전체삭제
		// 2. 단건등록
		// 3.등록건수 조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count:{}", count);

		// paging
		search.setSearchWord(dto01.getTitle());
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNo(1);

		// 3.
		List<NoticeDTO> list = mapper.doRetrieve(search);
		for (NoticeDTO vo : list) {
			log.debug("vo: {}", vo);
		}

		// 항목 선택
		NoticeDTO result = mapper.doSelectOne(list.get(0));
		log.debug(result);
	}

	//@Disabled
	@Test
	void doUpdate() {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doUpdate()                 │");
		log.debug("└────────────────────────────┘");
		// 1. 전체삭제
		// 2. 단건등록
		// 3. 전체조회 및 항목선택
		// 4. 수정
		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug("count: {}", count);

		// 3
		search.setSearchWord(dto01.getTitle());
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNo(1);
		List<NoticeDTO> list = (List<NoticeDTO>) mapper.doRetrieve(search);
		for (NoticeDTO noVO : list) {
			log.debug("noVO:{}", noVO);
		}
		list.get(0).setTitle("제목1");
		list.get(0).setContent("내용1");
		int result = mapper.doUpdate(list.get(0));
		log.debug(result);

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
		assertEquals(1, flag);

		// 4.건수비교
		count = mapper.getCount();
		log.debug("count:{}", count);
		assertEquals(0, count);
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
