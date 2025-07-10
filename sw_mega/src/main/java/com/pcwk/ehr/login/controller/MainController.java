package com.pcwk.ehr.login.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.login.service.MainService;

@Controller
@RequestMapping("/common")
public class MainController {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	MainService mainService;
	
	public MainController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ MainController()                                        │");
        log.debug("└─────────────────────────────────────────────────────────┘");
    }
	
	@GetMapping("/main.do")
    public String mainPage(Model model, HttpSession session) {
		
		
		String msg = mainService.getWelcomeMessage();
	    model.addAttribute("msg", msg);
	    
	    String userId = (String) session.getAttribute("userId");
	    model.addAttribute("userId",userId);
	    
        return "/common/main"; // /WEB-INF/views/main.jsp
    }
}
