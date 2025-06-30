package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

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

import com.pcwk.ehr.login.domain.FindIdDTO;
import com.pcwk.ehr.login.mapper.FindIdMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class FindIdDaoTest {
	Logger log=LogManager.getLogger(getClass());

    @Autowired
    FindIdMapper findIdMapper;
    
    @Autowired
	ApplicationContext context;
    
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	//@Disabled
	@Test
    public void findUserId() throws SQLException{
    FindIdDTO dto = new FindIdDTO();
    dto.setEmail("user1@test.com");
    String userId =  findIdMapper.findUserId(dto.getEmail());
    assertNotNull(userId);
    log.debug("조회된 userId: {}", userId);
    }

    @Test
    void beans() {
        assertNotNull(context);
        log.debug("context:{}"+context);
    }
}
