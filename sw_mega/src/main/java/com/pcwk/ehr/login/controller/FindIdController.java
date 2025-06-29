package com.pcwk.ehr.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pcwk.ehr.login.domain.FindIdDTO;
import com.pcwk.ehr.login.service.FindIdService;

@Controller
public class FindIdController {
	 @Autowired
	    FindIdService findIdService;

	    @PostMapping("/findId")
	    public String findId(@ModelAttribute FindIdDTO dto, Model model) {
	        String userId = findIdService.findId(dto);
	        if(userId != null) {
	            model.addAttribute("userId", userId);
	            return "findIdResult";
	        } else {
	            model.addAttribute("msg", "일치하는 정보가 없습니다.");
	            return "findId";
	        }
	    }
}
