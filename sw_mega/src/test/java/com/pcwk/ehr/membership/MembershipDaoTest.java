/**
 * Package Name : com.pcwk.ehr.membership
 * 파일명       : MembershipDaoTest.java
 */
package com.pcwk.ehr.membership;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.DTO.MembershipDTO;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MembershipDaoTest {

	final Logger log = LogManager.getLogger(getClass());

	
	
	@Autowired
	MembershipMapper mapper; // ← MyBatis Mapper

//	@Disabled
//	@BeforeEach
//	void clearDB() throws SQLException {
//	    mapper.deleteAllCascade();   // 자식 → 부모 삭제
//	}
//	
	
	@Autowired
	ApplicationContext context;

	MembershipDTO dto01;
	MembershipDTO dto02;
	MembershipDTO dto03;

	SearchDTO search;

	/* ─────────────────────────────────────────────────────────── */
	/* setUp / tearDown */
	/* ─────────────────────────────────────────────────────────── */
	@BeforeEach
	void setUp() throws Exception {
		log.debug("──────────────── setUp ────────────────");

//		MembershipDTO dto01 = new MembershipDTO(
//		        "userA01",                 // userId
//		        "adminA01",                // adminId
//		        "userA01@test.com",        // email
//		        "pwA!23",                  // password
//		        Date.valueOf("1992-05-12"),// birth
//		        "Y",                       // emailAuth (Y/N)
//		        "tokA1234XYZ",             // emailAuthToken
//		        2,                         // grade (1:BASIC, 2:SILVER, 3:GOLD 등)
//		        "profileA.png",            // profileImage
//		        Date.valueOf("2025-06-27") // regDt
//		);
//		//dto02 = new MembershipDTO("admin02", "user02", "pw02", Date.valueOf("1990-02-02"), "user02@test.com", "Y",
//				//"token456", 60, 15, 2, null, null);
//		//dto03 = new MembershipDTO("admin03", "user03", "pw03", Date.valueOf("1990-02-02"), "user03@test.com", "N",
//				//"token789", 120, 40, 3, null, null);
//
//		search = new SearchDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("──────────────── AfterEach ────────────────");
	}

	/* ─────────────────────────────────────────────────────────── */
	/* Bean 주입 확인 */
	/* ─────────────────────────────────────────────────────────── */
	@Test
	void beans() {
		assertNotNull(context);
		log.debug("context  : {}", context);
		
	}
	@Disabled
	@Test
	void doSelectOne() throws SQLException{
		//1. 전체삭제
		//2. 다건등록
		//3. 단건조회
		
		//1. 
		mapper.deleteAllCascade(); 
		
		//2. 
		mapper.doUpdate(dto01);
		
		//3. 
		MembershipDTO outVO = mapper.doSelectOne(dto01);
		assertEquals("admin01", outVO);
		
	}

	/* ─────────────────────────────────────────────────────────── */
	/* 목록 + 페이징 */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
	@Test
	void doRetrieve() throws SQLException {
		mapper.deleteAllCascade(); // 1. 전체 삭제
		int cnt = mapper.saveAll(); // 2. 대량 삽입(200건)
		assertEquals(200, cnt);

		search.setPageSize(10);
		search.setPageNo(1);
		search.setSearchDiv(""); // 검색 X
		search.setSearchWord("");

		List<MembershipDTO> list = mapper.doRetrieve(search);
		assertEquals(10, list.size()); // 첫 페이지 10건

		list.forEach(vo -> log.debug(vo));
	}

	/* ─────────────────────────────────────────────────────────── */
	/* 삭제 */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
	@Test
	void doDelete() throws SQLException {
		mapper.deleteAllCascade();
		assertEquals(0, mapper.getCount());

		mapper.doSave(dto01);
		assertEquals(1, mapper.getCount());

		int flag = mapper.doDelete(dto01);
		assertEquals(1, flag);
		assertEquals(0, mapper.getCount());
	}

	/* ─────────────────────────────────────────────────────────── */
	/* 수정 */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
	@Test
	void doUpdate() throws SQLException {
		mapper.deleteAllCascade();
		mapper.doSave(dto01);

		MembershipDTO saved = mapper.doSelectOne(dto01);
		assertNotNull(saved);
		isSame(saved, dto01);

		// 필드 수정
		saved.setEmail(saved.getEmail() + "_U");
		saved.setPassword(saved.getPassword() + "_U");
		//saved.setLogin(saved.getLogin() + 99);
		//aved.setRecommend(saved.getRecommend() + 99);
		saved.setGrade(3); // GOLD

		int flag = mapper.doUpdate(saved);
		assertEquals(1, flag);

		MembershipDTO updated = mapper.doSelectOne(saved);
		isSame(updated, saved);
	}

	/* ─────────────────────────────────────────────────────────── */
	/* 전체 조회 */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
	@Test
	void getAll() throws SQLException {
		mapper.deleteAllCascade();

		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);
		assertEquals(3, mapper.getCount());

		List<MembershipDTO> list = mapper.getAll();
		assertEquals(3, list.size());
	}

	/* ─────────────────────────────────────────────────────────── */
	/* 없는 데이터 조회 시 예외 */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
	@Test
	void getFailure() throws SQLException {
		mapper.deleteAllCascade();
		mapper.doSave(dto01);

		dto01.setUserId(dto01.getUserId() + "_X"); // 존재하지 않는 ID

		assertThrows(EmptyResultDataAccessException.class, () -> {
			mapper.doSelectOne(dto01);
		});
	}

	/* ─────────────────────────────────────────────────────────── */
	/* add & get */
	/* ─────────────────────────────────────────────────────────── */
	@Disabled
      @Test
	void addAndGet() throws SQLException {
		mapper.deleteAllCascade();

		mapper.doSave(dto01);
		mapper.doSave(dto02);
		mapper.doSave(dto03);

		isSame(mapper.doSelectOne(dto01), dto01);
		isSame(mapper.doSelectOne(dto02), dto02);
		isSame(mapper.doSelectOne(dto03), dto03);
	}

	/* ─────────────────────────────────────────────────────────── */
	/* VO 동등성 비교 Helper */
	/* ─────────────────────────────────────────────────────────── */
	private void isSame(MembershipDTO a, MembershipDTO b) {
		assertEquals(a.getUserId(), b.getUserId());
		assertEquals(a.getAdminId(), b.getAdminId());
		assertEquals(a.getEmail(), b.getEmail());
		assertEquals(a.getPassword(), b.getPassword());
		//assertEquals(a.getLogin(), b.getLogin());
		//assertEquals(a.getRecommend(), b.getRecommend());
		assertEquals(a.getGrade(), b.getGrade());
	}
}
