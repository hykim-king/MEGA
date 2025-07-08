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
	     return "findId";
	 }
	 
	 @PostMapping(value="/findId.do", produces="text/plain;charset=UTF-8")
	 public String findId(@ModelAttribute FindIdDTO dto, Model model) throws SQLException {
		 log.debug("┌────────────────────────┐");
		 log.debug("│ findIdView()           │");
		 log.debug("└────────────────────────┘");
		 
		 String userId = findIdService.findId(dto);
	     if(userId != null && !userId.trim().isEmpty()) {
	         model.addAttribute("userId", userId);
	         return "findIdResult";
	     } else {
	         model.addAttribute("msg", "일치하는 정보가 없습니다.");
	         return "findId";
	     }
	 }
}
