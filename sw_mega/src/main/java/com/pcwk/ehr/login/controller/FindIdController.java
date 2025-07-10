package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.pcwk.ehr.login.domain.FindIdDTO;
import com.pcwk.ehr.login.service.FindIdService;
import com.pcwk.ehr.mapper.FindIdMapper;

@Controller
@RequestMapping("/login")
public class FindIdController{
	Logger log = LogManager.getLogger(getClass());
	
	 @Autowired
	 FindIdService findIdService;
	 
	 @Autowired
	 FindIdMapper findIdMapper;
	 
	 public FindIdController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ FindIdController()                                      │");
		log.debug("└─────────────────────────────────────────────────────────┘");

	 }
	 
	 @GetMapping("/findId.do")
	 public String findIdForm() {
		 log.debug("┌────────────────────────┐");
		 log.debug("│ findIdForm()           │");
		 log.debug("└────────────────────────┘");
		 
	     return "/login/findId";
	 }
	 
	 @PostMapping("/findIdView.do")
	 public String findId(@ModelAttribute FindIdDTO dto, Model model) throws SQLException {
		 log.debug("┌────────────────────────┐");
		 log.debug("│ findIdView()           │");
		 log.debug("└────────────────────────┘");
		 
		 String userId = findIdService.findId(dto);
		 if(userId != null && !userId.trim().isEmpty()) {
		        // 첫 글자만 남기고 나머지는 *로 마스킹
		        String maskedId;
		        if(userId.length() == 1) {
		            maskedId = userId;
		        } else {
		            StringBuilder sb = new StringBuilder();
		            sb.append(userId.charAt(0));
		            for (int i = 1; i < userId.length(); i++) sb.append("*");
		            maskedId = sb.toString();
		        }
		        model.addAttribute("maskedId", maskedId);
		        return "/login/findIdResult";
		    } else {
		        model.addAttribute("msg", "일치하는 정보가 없습니다.");
		        return "findId";
		    }
	 }
}
