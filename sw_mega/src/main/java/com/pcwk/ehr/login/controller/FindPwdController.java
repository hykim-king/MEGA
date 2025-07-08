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

import com.pcwk.ehr.login.domain.FindPwdDTO;
import com.pcwk.ehr.login.service.FindPwdService;

@Controller
@RequestMapping("/login")
public class FindPwdController{
	Logger log = LogManager.getLogger(getClass());

    @Autowired
    FindPwdService findPwdService;

    public FindPwdController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ FindPwdController()                                     │");
        log.debug("└─────────────────────────────────────────────────────────┘");
    }

    // 비밀번호 찾기 입력 폼(GET)
    @GetMapping("/findPwd.do")
    public String findPwdForm() {
        return "findPwd";
    }

    // 비밀번호 찾기 실행(POST)
    @PostMapping("/findPwd.do")
    public String doFindPwd(@ModelAttribute FindPwdDTO dto, Model model) throws SQLException {
        String password = findPwdService.findPwd(dto);
        if(password != null && !password.trim().isEmpty()) {
            model.addAttribute("password", password);
            return "findPwdResult";
        } else {
            model.addAttribute("msg", "일치하는 정보가 없습니다. 다시 입력해주세요.");
            return "findPwd";
        }
    }
}
