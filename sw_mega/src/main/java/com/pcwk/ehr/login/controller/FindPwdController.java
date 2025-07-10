package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.service.FindPwdService;

@Controller
@RequestMapping("/login")
public class FindPwdController{
	Logger log = LogManager.getLogger(getClass());

    @Autowired
    FindPwdService findPwdService;
    
    @Autowired
    JavaMailSender mailSender;

    public FindPwdController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ FindPwdController()                                     │");
        log.debug("└─────────────────────────────────────────────────────────┘");
    }

    // 비밀번호 찾기 입력 폼(GET)
    @GetMapping("/findPwd.do")
    public String findPwdForm() {
    	 log.debug("┌────────────────────────┐");
		 log.debug("│ findPwdForm()          │");
		 log.debug("└────────────────────────┘");
        return "/login/findPwd";
    }

    // 비밀번호 찾기 실행(POST)
    @PostMapping("/findPwdView.do")
    public String doFindPwd(@ModelAttribute FindPwdDTO dto, Model model) throws SQLException {
    	 log.debug("┌────────────────────────┐");
		 log.debug("│ findPwdView()          │");
		 log.debug("└────────────────────────┘");
    	String password = findPwdService.findPwd(dto);
        if (password != null && !password.trim().isEmpty()) {
            // 메일 발송
            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(dto.getEmail());
                mail.setSubject("[Hellmate] 비밀번호 찾기 안내");
                mail.setText("요청하신 비밀번호는: " + password + " 입니다.\n로그인 후 반드시 비밀번호를 변경해 주세요.");
                mailSender.send(mail);

                model.addAttribute("mailSent", "등록된 이메일로 비밀번호가 발송되었습니다. 메일을 확인하세요.");
            } catch (Exception e) {
                log.error("메일 발송 실패", e);
                model.addAttribute("msg", "메일 발송에 실패했습니다. 다시 시도해주세요.");
            }
        } else {
            model.addAttribute("msg", "가입하지 않은 이메일(아이디)입니다. 다시한번 확인해주세요.");
        }
        return "/login/findPwdView";
    }
}
