package com.pcwk.ehr.membership.controller;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.membership.service.MembershipService;



/**
 * 〈Membership CRUD Controller〉  
 *  URL 패턴(모두 .do)  
 *   1) GET  /membership/doSaveView.do    : 등록 화면  
 *   2) POST /membership/doSave.do        : 등록 처리  
 *   3) GET  /membership/doRetrieve.do    : 목록 조회  
 *   4) GET  /membership/doSelectOne.do   : 단건 상세  
 *   5) GET  /membership/doUpdateView.do  : 수정 화면  
 *   6) POST /membership/doUpdate.do      : 수정 처리  
 *   7) POST /membership/doDelete.do      : 삭제  
 */
@Controller
@RequestMapping("/membership")
public class MembershipController implements PLog {

    @Autowired
    private MembershipService membershipService;
    
    @Autowired
    private JavaMailSender mailSender;

    /*───────────────────────────────────────────────────────────*/
    /* Constructor                                               */
    /*───────────────────────────────────────────────────────────*/
    public MembershipController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ MembershipController()                                  │");
        log.debug("└─────────────────────────────────────────────────────────┘");
    }

    /*───────────────────────────────────────────────────────────*/
    /* 1. 등록 화면                                              */
    /*───────────────────────────────────────────────────────────*/
    @GetMapping("/doSaveView.do")
    public String doSaveView() {
        String viewName = "membership/membership_mng";  // /WEB-INF/views/...
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doSaveView()*            │");
        log.debug("└───────────────────────────┘");
        return viewName;
    }

    /*───────────────────────────────────────────────────────────*/
    /* 2. 등록 처리                                              */
    /*───────────────────────────────────────────────────────────*/
    @PostMapping("/doSave.do")
    public String doSave(MembershipDTO param, String UTF_8) throws SQLException, UnsupportedEncodingException {
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doSave()* param: {}      │", param);
        log.debug("└───────────────────────────┘");

        int flag = membershipService.save(param);

        // 등록 성공 여부에 따라 URL에 파라미터 추가
        String msg        = (flag == 1) ? "회원가입 성공" : "회원가입 실패";
        String encodedMsg = java.net.URLEncoder.encode(msg, "UTF-8");
        return "redirect:/membership/doSaveView.do?success=" + (flag == 1)
               + "&msg=" + encodedMsg;
    }

    /*───────────────────────────────────────────────────────────*/
    /* 3. 목록 조회 (Search + Paging)                            */
    /*───────────────────────────────────────────────────────────*/
    @GetMapping("/doRetrieve.do")
    public String doRetrieve(SearchDTO search, Model model) throws SQLException {
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doRetrieve()* search={}  │", search);
        log.debug("└───────────────────────────┘");

        List<MembershipDTO> list = membershipService.retrieve(search);
        model.addAttribute("list", list);
        model.addAttribute("search", search); // 검색조건 유지용

        return "membership/membership_list";
    }

    /*───────────────────────────────────────────────────────────*/
    /* 4. 단건 상세                                              */
    /*───────────────────────────────────────────────────────────*/
    @GetMapping ("/doSelectOne.do")
    public String doSelectOne(@RequestParam String userId, Model model) {
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doSelectOne()* userId={} │", userId);
        log.debug("└───────────────────────────┘");

        MembershipDTO inVO = new MembershipDTO();
        inVO.setUserId(userId);

        MembershipDTO outVO = null;
		try {
			outVO = membershipService.selectOne(inVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("member", outVO);

        return "membership/membership_detail";
    }

    /*───────────────────────────────────────────────────────────*/
    /* 5. 수정 화면                                              */
    /*───────────────────────────────────────────────────────────*/
    
    @GetMapping("/doUpdateView.do")
    
    
    
    public String doUpdateView(@RequestParam String userId, Model model) {
        // ───────────────────────── 로그 ─────────────────────────
        log.debug("┌───────────────────────────────┐");
        log.debug("│ *doUpdateView()* userId={}     │", userId);
        log.debug("└───────────────────────────────┘");

        // ───────────────────────── 비즈니스 로직 ─────────────────────────
        MembershipDTO inVO = new MembershipDTO();
        inVO.setUserId(userId);

        try {
			model.addAttribute("member",
			                   membershipService.selectOne(inVO));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return "membership/membership_update";
    }


    /*───────────────────────────────────────────────────────────*/
    /* 6. 수정 처리                                              */
    /*───────────────────────────────────────────────────────────*/
    @PostMapping("/doUpdate.do")
    public String doUpdate(MembershipDTO dto, Model model) {
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doUpdate()* dto={}       │", dto);
        log.debug("└───────────────────────────┘");

        int flag = membershipService.update(dto);
        model.addAttribute("msg", (flag == 1) ? "수정 성공" : "수정 실패");

        return "redirect:/membership/doSelectOne.do?userId=" + dto.getUserId();
    }

    /*───────────────────────────────────────────────────────────*/
    /* 7. 삭제                                                   */
    /*───────────────────────────────────────────────────────────*/
    @PostMapping("/doDelete.do")
    public String doDelete(@RequestParam String userId, Model model) {
        log.debug("┌───────────────────────────┐");
        log.debug("│ *doDelete()* userId={}    │", userId);
        log.debug("└───────────────────────────┘");

        MembershipDTO dto = new MembershipDTO();
        dto.setUserId(userId);

        int flag = membershipService.delete(dto);
        model.addAttribute("msg", (flag == 1) ? "삭제 성공" : "삭제 실패");

        return "redirect:/membership/doRetrieve.do";
    }

    /*───────────────────────────────────────────────────────────*/
    /* 8. 아이디/이메일 중복 체크 (AJAX)                            */
    /*───────────────────────────────────────────────────────────*/
    @ResponseBody
    @GetMapping("/checkUserId.do")
    public boolean checkUserId(@RequestParam String userId) throws SQLException {
        return membershipService.isUserIdAvailable(userId);   // true → 사용 가능
    }

    @ResponseBody
    @GetMapping("/checkEmail.do")
    public boolean checkEmail(@RequestParam String email) throws SQLException {
        return membershipService.isEmailAvailable(email);     // true → 사용 가능
    }
    
    @GetMapping("/idCheck.do")           //아이디 중복여부 확인
    @ResponseBody
    public String idCheck(@RequestParam String userId) throws SQLException {
        MembershipDTO dto = new MembershipDTO();
        dto.setUserId(userId);

        MembershipDTO result = membershipService.selectOne(dto);

        return (result == null) ? "0" : "1";  // 0: 사용 가능, 1: 중복
    }

    
    /*───────────────────────────────────────────────────────────*/
    /* 9. 이메일 인증 링크 처리                                   */
    /*───────────────────────────────────────────────────────────*/
    @GetMapping("/verifyEmail.do")
    public String verifyEmail(@RequestParam String token, Model model) throws SQLException {
        boolean ok = membershipService.verifyEmailToken(token);
        model.addAttribute("msg",
                ok ? "이메일 인증이 완료되었습니다!" : "유효하지 않은 인증 링크입니다.");
        return "membership/verify_result";   // 결과 화면 JSP
    }

    @PostMapping("/sendAuthCode.do")
    @ResponseBody
    public String sendAuthCode(@RequestParam String email) throws Exception {
        // 1) 6자리 랜덤 코드 생성
        String code = String.format("%06d", (int)(Math.random()*1_000_000));

        
       
        
        // 2) 메일 발송
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("com0494@naver.com"); 
        mail.setTo(email);
        mail.setSubject("[MEGA] 이메일 인증 코드");
        mail.setText("인증코드: " + code);
        //발송
        mailSender.send(mail);

        // 3) DB에 토큰·만료시간 저장 (updateEmailAuthToken)
        membershipService.saveEmailToken(email, code);

        return "OK";
    }


}
