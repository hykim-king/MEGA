package com.pcwk.ehr.login;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.login.dao.LoginDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class LoginDaoTest {
	Logger log=LogManager.getLogger(getClass());

    @Autowired
    LoginDao loginDao;
    
    @Autowired
	ApplicationContext context;

    @Test
    void daoNotNullTest() {
        assertNotNull(loginDao, "LoginDao가 정상적으로 주입되어야 합니다.");
        log.debug("context: "+context);
    }
}
