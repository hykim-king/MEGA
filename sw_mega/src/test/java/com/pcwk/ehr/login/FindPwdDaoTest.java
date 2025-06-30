package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;

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
import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.mapper.FindPwdMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class FindPwdDaoTest {
	Logger log=LogManager.getLogger(getClass());

    @Autowired
    FindPwdMapper findPwdMapper;
    
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
	void findPwd() {
		FindPwdDTO dto = new FindPwdDTO();
		dto.setUserId("user1");
	    dto.setEmail("user");
	    String password =  findPwdMapper.findPwd(dto);
	    assertNotNull(password);
	    log.debug("조회된 password: {}", password);
	}

    @Test
    void beans() {
        assertNotNull(context);
        log.debug("context:{}"+context);
    }
}
