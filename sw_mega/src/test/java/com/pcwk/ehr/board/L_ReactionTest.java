package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.board.mapper.L_ReactionMapper;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

class L_ReactionTest {

    final Logger log = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext context;
    
    @Autowired
    L_ReactionMapper reactionMapper;
    

    
	@BeforeEach
	void setUp() throws Exception {
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

	@Test
    @DisplayName("doSave() 좋아요 등록 테스트")
	void doSave()  {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");

		   // DTO 객체 생성
	    L_ReactionDTO dto = new L_ReactionDTO(
	        0,              // reactionCode (시퀀스로 자동 처리됨)
	        "testUser",     // userId
	        "Y",            // reactionType: 'Y' → 좋아요
	        "NOTICE",       // targetType
	        100             // targetCode
	    );

	    int result = reactionMapper.doSave(dto);
	    assertEquals(1, result, "❌ 좋아요 등록 실패");
	}
	
	
	// @Disabled
		@Test
		void beans() {

			assertNotNull(context);
			log.debug(context);
	}

}
