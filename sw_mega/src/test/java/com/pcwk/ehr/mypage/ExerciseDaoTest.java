package com.pcwk.ehr.mypage;

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

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ExerciseMapper;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class ExerciseDaoTest {

	@Autowired
	ExerciseMapper mapper;

	ExerciseDTO dto01;
	ExerciseDTO dto02;
	ExerciseDTO dto03;

	SearchDTO search;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	public void setUp() throws Exception {

		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		dto01 = new ExerciseDTO("달리기", "유산소", null, "남성", 70, 150);
		dto02 = new ExerciseDTO("벤치프레스", "근력", "가슴", null, null, 100);
		dto03 = new ExerciseDTO("달리기", "유산소", null, "여성", 45, 100);

		search = new SearchDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Disabled
	@Test
	void doDelete() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		// 1. 전체삭제
		// 2. 단건등록
		// 3. 전체조회
		// 4. 단건삭제
		// 5. 등록 건수 조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);

		// paging
		search.setPageSize(10);
		search.setPageNo(1);
	
		// 검색필터
		search.setSearchDiv("10");
		search.setSearchWord("달리기");
	
		// 3.
		List<ExerciseDTO> list = mapper.doRetrieve(search);
		for (ExerciseDTO vo : list) {
			log.debug("vo: {}", vo);
		}
		
		
		// 4.
		ExerciseDTO param = new ExerciseDTO();
		param.seteCode(list.get(0).geteCode());
	    mapper.doDelete(param);
	

		// 4.
		int count = mapper.getCount();
		assertEquals(0, count);
		log.debug("count: {}", count);

	}

	@Disabled
	@Test
	void doUpdate() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		// 1. 전체삭제
		// 2. 단건등록
		// 2.1 등록건수 비교
		// 3. 전체조회
		// 4. 수정하기

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 2.1 등록 건수 비교
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug(count);

		// paging
		search.setPageSize(10);
		search.setPageNo(1);

		// 검색필터
		search.setSearchDiv("10");
		search.setSearchWord("달리기");

		// 3.
		List<ExerciseDTO> list = mapper.doRetrieve(search);
		for (ExerciseDTO vo : list) {
			log.debug("vo: {}", vo);
		}

		// 항목 선택
		ExerciseDTO inVO = list.get(0);
		log.debug("inVO: {}", inVO);

		// 4.
		inVO.setWeight(80);
		log.debug(inVO);

		int result = mapper.doUpdate(inVO);

		assertEquals(1, result);
		log.debug("result: {}", result);

	}

	@Disabled
	@Test
	void doSelectOne() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		// 1. 전체삭제
		// 2. 단건등록
		// 2.1 등록 건수 비교
		// 3. 전체조회
		// 3.1 항목 선택
		// 4. 단건조회

		// 1.
		mapper.deleteAll();

		// 2.
		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		// 2.1 등록 건수 비교
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug(count);

		// paging
		search.setPageSize(10);
		search.setPageNo(1);

		// 검색필터
		search.setSearchDiv("10");
		search.setSearchWord("달리기");

		// 3.
		List<ExerciseDTO> list = mapper.doRetrieve(search);
		for (ExerciseDTO vo : list) {
			log.debug("vo: {}", vo);
		}

		// 항목 선택
		int selectedCode = list.get(0).geteCode();
		log.debug(selectedCode);

		// 4.
		ExerciseDTO param = new ExerciseDTO();
		param.seteCode(selectedCode);
		ExerciseDTO result = mapper.doSelectOne(param);

		assertEquals(selectedCode, result.geteCode());
		log.debug("result: {}", result);

	}

	//@Disabled
	@Test
	void doRetrieve() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                                            │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		// 1. 전체삭제
		// 2. 다건등록
		// paging
		// 검색필터
		// 3. 조회

		// 1.
		mapper.deleteAll();

		// 2.
		int count = mapper.saveAll();
		log.debug("count" + count);

		assertEquals(502, count);

		// paging
		search.setPageSize(10);
		search.setPageNo(2);

		// 검색필터
		search.setSearchDiv("20");
		search.setSearchWord("가슴");

		// 3.
		List<ExerciseDTO> list = mapper.doRetrieve(search);
		for (ExerciseDTO vo : list) {
			log.debug("vo: {}", vo);
		}

	}

	@Test
	void beans() {
		assertNotNull(context);

		log.debug(context);
	}

}
