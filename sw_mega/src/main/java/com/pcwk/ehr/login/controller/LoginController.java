package com.pcwk.ehr.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	 @Autowired
	    LoginService loginService;

	    @PostMapping("/login")
	    public String login(@ModelAttribute LoginDTO dto, HttpSession session) {
	    	LoginDTO user = loginService.login(dto.getUserId(), dto.getPassword());
	        if(user != null) {
	            session.setAttribute("userId", user.getUserId());
	            return "redirect:/main";
	        } else {
	            return "login"; // 로그인 실패
	        }
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/login";
	    }
}
