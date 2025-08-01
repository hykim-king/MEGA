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
import com.pcwk.ehr.mapper.FoodMapper;
import com.pcwk.ehr.mypage.domain.FoodDTO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" 
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class FoodDaoTest {
	
	@Autowired
	FoodMapper mapper;
	
	FoodDTO dto01;
	FoodDTO dto02;
	FoodDTO dto03;
	
	SearchDTO search;
	
	@Autowired
	ApplicationContext context;
	
	Logger log = LogManager.getLogger(getClass());

	@BeforeEach //main에서 생성자 역할
	public void setUp() throws Exception {
		
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		dto01 = new FoodDTO("김치볶음밥", 200, 430, 30, 20, 20, 30);
		dto02 = new FoodDTO("된장찌개", 200, 430, 30, 20, 20, 30);
		dto03 = new FoodDTO("삽겹살", 200, 430, 30, 20, 20, 30);
		
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
	void doDelete() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doDelete()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 삭제
		//4. 등록 건수 조회
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01); 
		
		//3. 
		FoodDTO param = new FoodDTO();
		param.setFoodName("김치볶음밥");
		int result = mapper.doDelete(param);
		
		assertEquals(1, result);
		
		//4.
		int count = mapper.getCount();
		log.debug(count);
		
	}
	
	@Disabled
	@Test
	void doUpdate() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doUpdate()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록
		//3. 단건조회 및 값 변경
		//4. 수정하기
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01); 
		
		//3. 
		FoodDTO existing = mapper.doSelectOne(dto01);
		existing.setCal(50); //cal 값만 변경
		
//		FoodDTO param = new FoodDTO();
//		param.setFoodName("김치볶음밥");
//		param.setCal(20);
		
		//4.
		int result = mapper.doUpdate(existing);
		
		assertEquals(1, result);
		
	}
	
	@Disabled
	@Test
	void doSelectOne()throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doSelectOne()                                           │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 단건등록 x 3
		//2.1 등록 건수 비교
		//3. 단건조회
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		mapper.doSave(dto01); 
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		
		//2.1 등록 건수 비교
		int count = mapper.getCount();
		assertEquals(3, count);
		log.debug(count);
		
		//3. 
		FoodDTO param = new FoodDTO(); 
		param.setFoodName("된장찌개");
		FoodDTO result = mapper.doSelectOne(param);
		
		assertEquals("된장찌개", result.getFoodName());
		
		
	}
	
	//@Disabled
	@Test
	void doRetrieve() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                                            │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//1. 전체삭제
		//2. 다건등록
		//3. paging 조회
		//4. 유사어 조회 
		
		//1. 
		mapper.deleteAll();
		
		//2. 
		int count = mapper.saveAll();
		log.debug("count" + count);
		
		assertEquals(502, count);
		
		//paging
		search.setPageSize(10);
		search.setPageNo(1);
		
		//검색
		search.setSearchWord("김치볶음밥");
		
		//3.
		List<FoodDTO> list = mapper.doRetrieve(search);
		for(FoodDTO vo : list) {
			log.debug(vo);
		}
		
	}
	

	//@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(mapper);
		
		log.debug(context);
		log.debug(mapper);
		
	}

}
