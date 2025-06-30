package com.pcwk.ehr.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.service.FindPwdService;

@Controller
public class FindPwdController {
	 @Autowired
	    FindPwdService findPwdService;

	    @PostMapping("/findPwd")
	    public String findPwd(@ModelAttribute FindPwdDTO dto, Model model) {
	        boolean result = findPwdService.resetPassword(dto);
	        if(result) {
	            model.addAttribute("msg", "임시 비밀번호가 발급되었습니다. 이메일을 확인하세요.");
	            return "findPwdResult";
	        } else {
	            model.addAttribute("msg", "정보가 일치하지 않습니다.");
	            return "findPwd";
	        }
	    }
}
