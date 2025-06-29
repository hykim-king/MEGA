package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.board.mapper.FbCommentMapper;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

class Fb_CommentTest {
    final Logger log = LogManager.getLogger(getClass());

    @Autowired
    ApplicationContext context;

    @Autowired
    FbCommentMapper fbCommentMapper;
    
    
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ setUp()                    │");
		log.debug("└────────────────────────────┘");
	}

    @Test
    @DisplayName("doSave() 댓글 등록 테스트")
    void doSave() {
    	FreeBoardCommentDTO dto = new FreeBoardCommentDTO(
    	        0,              // commentedCode (시퀀스로 자동 처리됨)
    	        1,              // noCode (공지사항 코드)
    	        "testUser",     // userId
    	        "테스트 댓글입니다", // content
    	        new Date(),     // cDt
    	        new Date()      // upDt
    	    );
        int result = fbCommentMapper.doSave(dto);
        assertEquals(1, result, "❌ 댓글 등록 실패");
        
    }
	
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌────────────────────────────┐");
		log.debug("│ tearDown()                 │");
		log.debug("└────────────────────────────┘");
	}

	//@Disabled
    @Test
    void beans() {
        assertNotNull(context);
        assertNotNull(context);
	}

}
