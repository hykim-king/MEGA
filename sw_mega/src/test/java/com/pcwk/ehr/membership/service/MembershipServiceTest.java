
package com.pcwk.ehr.membership.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class MembershipServiceTest {

	@Autowired

	@Qualifier("membershipServiceImpl") // 스프링이 어떤 Bean을 주입해야 할지 모호할 때
	MembershipService service;

	@Autowired
	MembershipMapper mapper;

	MembershipDTO dto;

	@BeforeEach
	void setUp() throws SQLException {
		mapper.deleteAll();

		dto = new MembershipDTO("userA01", "adminA01", "userA01@test.com", "pwA!23",
				new Date(System.currentTimeMillis()), "N", "tokA1234XYZ", 1, "profileA.png",
				Date.valueOf("2025-05-12"));
	}

	@Test
	void testSaveAndSelectOne() throws SQLException {
		assertTrue(service.isUserIdAvailable(dto.getUserId()));

		assertEquals(1, service.save(dto));

		MembershipDTO result = service.selectOne(dto);
		assertEquals(dto.getUserId(), result.getUserId());
	}
	
	@Test
	void dtoValidationFail() {
	    MembershipDTO dto = new MembershipDTO();           // 필수값 비움

	    Set<ConstraintViolation<MembershipDTO>> violations =
	            Validation.buildDefaultValidatorFactory()
	                      .getValidator()
	                      .validate(dto);

	    assertFalse(violations.isEmpty());                 // 필수값 누락 → 오류 기대
	}

	
	
	
	//@Disabled
	@Test
	void testRetrieve() throws SQLException {
		service.save(dto);

		SearchDTO search = new SearchDTO();
		search.setPageSize(10);
		search.setPageNo(1);
		search.setSearchDiv("");
		search.setSearchWord("");

		List<MembershipDTO> list = service.retrieve(search);

		assertEquals(1, list.size());
		assertEquals(dto.getUserId(), list.get(0).getUserId());
	}

	@Test
	void testIsUserIdAvailable() throws SQLException {
		assertTrue(service.isUserIdAvailable("newuser"));

		service.save(dto);

		assertFalse(service.isUserIdAvailable(dto.getUserId()));
	}
}