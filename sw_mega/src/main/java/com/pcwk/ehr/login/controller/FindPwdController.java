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

	 @PostMapping("/findPwd.do")
	 public String findPwd(@ModelAttribute FindPwdDTO inDTO, Model model) {
	     String password = findPwdService.findPwd(inDTO);  // 서비스 호출

	     if (password != null) {
	         model.addAttribute("message", "비밀번호는: " + password);
	     } else {
	         model.addAttribute("message", "입력한 정보로 등록된 비밀번호가 없습니다.");
	     }

	     return "login/findPwdResult"; // WEB-INF/views/login/findPwdResult.jsp
	 }

}
