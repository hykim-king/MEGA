package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.login.domain.LoginDTO;
import com.pcwk.ehr.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController{
	Logger log = LogManager.getLogger(getClass());

    @Autowired
    @Qualifier("loginServiceImpl")
    LoginService loginService;

    public LoginController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ LoginController()                                       │");
        log.debug("└─────────────────────────────────────────────────────────┘");
    }

    // 로그인 폼(GET)
    @GetMapping("/login.do")
    public String loginForm() {
        return "/login/login";
    }

    // 로그인 실행(POST)
    @PostMapping("/loginView.do")
    public String doLogin(@ModelAttribute LoginDTO dto, Model model, HttpSession session) throws SQLException {
    	  
    	  log.debug("LoginController#doLogin param: {}", dto);
          LoginDTO result = loginService.doSelectOne(dto);
          boolean isLogin = (result != null);

          if(isLogin) {
              // 세션에 사용자 정보 저장
              session.setAttribute("userId", result.getUserId());
              // 필요하다면 사용자 전체 정보 result를 session에 저장 가능
              model.addAttribute("loginResult", true);
          } else {
              model.addAttribute("msg", "일치하는 정보가 없습니다. 다시 입력해주세요.");
          }
          // 항상 login.jsp로 (alert 이후 이동 처리)
          return "login";
      }
  }