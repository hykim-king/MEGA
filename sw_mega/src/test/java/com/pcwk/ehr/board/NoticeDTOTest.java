package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.pcwk.ehr.board.mapper.NoticeMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class NoticeDTOTest {
	@Autowired
	NoticeMapper noticeMapper;

	NoticeDTO dto01;
	NoticeDTO dto02;
	NoticeDTO dto03;

	@Autowired
	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());

	@BeforeEach
	void setup() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");
		
		

	} 

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}

	// @Disabled
	@Test
	void beans() {

		assertNotNull(context);
		log.debug(context);
	}
}
