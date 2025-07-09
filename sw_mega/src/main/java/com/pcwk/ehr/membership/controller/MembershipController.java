package com.pcwk.ehr.membership.controller;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import com.pcwk.ehr.membership.service.MembershipService;

@Controller
@RequestMapping("/membership")           // 모든 URL 앞에 /membership
public class MembershipController implements PLog {

    @Autowired private MembershipService membershipService;
    @Autowired private JavaMailSender    mailSender;

    /*──────────────────────────────────────────────────────*/
    /* 1. 등록 화면                                         */
    /*──────────────────────────────────────────────────────*/
    @GetMapping("/doSaveView.do")
    public String doSaveView() {
        return "membership/membership_mng";   // /WEB-INF/views/...
    }

    /*──────────────────────────────────────────────────────*/
    /* 2. 등록 처리 → 성공 시 로그인 페이지로 redirect        */
    /*──────────────────────────────────────────────────────*/
    @PostMapping("/doSave.do")                // 실제 URL : /membership/doSave.do
    public String doSave(@ModelAttribute MembershipDTO dto,
                         RedirectAttributes rttr) throws SQLException {

        int cnt = membershipService.save(dto);

        if (cnt == 1) { // ── 성공
            rttr.addFlashAttribute("msg", "회원가입이 완료되었습니다. 로그인 해주세요!");
            return "redirect:/login.do";
        } else {        // ── 실패
            rttr.addFlashAttribute("msg", "회원가입 실패! 다시 시도해주세요.");
            return "redirect:/membership/doSaveView.do";
        }
    }

    /*──────────────────────────────────────────────────────*/
    /* 3. 목록 조회                                         */
    /*──────────────────────────────────────────────────────*/
    @GetMapping("/doRetrieve.do")
    public String doRetrieve(SearchDTO search, Model model) throws SQLException {
        List<MembershipDTO> list = membershipService.retrieve(search);
        model.addAttribute("list",   list);
        model.addAttribute("search", search);   // 검색조건 유지
        return "membership/membership_list";
    }

    /*──────────────────────────────────────────────────────*/
    /* 4. 단건 상세                                         */
    /*──────────────────────────────────────────────────────*/
    @GetMapping("/doSelectOne.do")
    public String doSelectOne(@RequestParam String userId, Model model)
                              throws SQLException {

        MembershipDTO inVO = new MembershipDTO();
        inVO.setUserId(userId);

        model.addAttribute("member", membershipService.selectOne(inVO));
        return "membership/membership_detail";
    }

    /*──────────────────────────────────────────────────────*/
    /* 5. 수정 화면                                         */
    /*──────────────────────────────────────────────────────*/
    @GetMapping("/doUpdateView.do")
    public String doUpdateView(@RequestParam String userId, Model model)
                               throws SQLException {

        MembershipDTO inVO = new MembershipDTO();
        inVO.setUserId(userId);

        model.addAttribute("member", membershipService.selectOne(inVO));
        return "membership/membership_update";
    }

    /*──────────────────────────────────────────────────────*/
    /* 6. 수정 처리                                         */
    /*──────────────────────────────────────────────────────*/
    @PostMapping("/doUpdate.do")
    public String doUpdate(MembershipDTO dto, RedirectAttributes rttr)
                           throws SQLException {

        int flag = membershipService.update(dto);
        rttr.addFlashAttribute("msg", (flag == 1) ? "수정 성공" : "수정 실패");
        return "redirect:/membership/doSelectOne.do?userId=" + dto.getUserId();
    }

    /*──────────────────────────────────────────────────────*/
    /* 7. 삭제                                              */
    /*──────────────────────────────────────────────────────*/
    @PostMapping("/doDelete.do")
    public String doDelete(@RequestParam String userId,
                           RedirectAttributes rttr) throws SQLException {

        MembershipDTO dto = new MembershipDTO();
        dto.setUserId(userId);

        int flag = membershipService.delete(dto);
        rttr.addFlashAttribute("msg", (flag == 1) ? "삭제 성공" : "삭제 실패");
        return "redirect:/membership/doRetrieve.do";
    }

    /*──────────────────────────────────────────────────────*/
    /* 8. AJAX 중복 체크                                    */
    /*──────────────────────────────────────────────────────*/
    @ResponseBody
    @GetMapping("/idCheck.do")
    public String idCheck(@RequestParam String userId) throws SQLException {
        MembershipDTO dto = new MembershipDTO();
        dto.setUserId(userId);
        return (membershipService.selectOne(dto) == null) ? "0" : "1";
    }

    @ResponseBody
    @GetMapping("/checkEmail.do")
    public boolean checkEmail(@RequestParam String email) throws SQLException {
        return membershipService.isEmailAvailable(email);
    }

    /*──────────────────────────────────────────────────────*/
    /* 9. 이메일 인증 토큰                                 */
    /*──────────────────────────────────────────────────────*/
    @PostMapping("/sendAuthCode.do")
    @ResponseBody
    public String sendAuthCode(@RequestParam String email) throws Exception {

        String code = String.format("%06d", (int)(Math.random()*1_000_000));

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("com0494@naver.com");
        mail.setTo(email);
        mail.setSubject("[MEGA] 이메일 인증 코드");
        mail.setText("인증코드: " + code);
        mailSender.send(mail);

        membershipService.saveEmailToken(email, code);
        return "OK";
    }

    @GetMapping("/verifyEmail.do")
    public String verifyEmail(@RequestParam String token, Model model)
                              throws SQLException {
        boolean ok = membershipService.verifyEmailToken(token);
        model.addAttribute("msg", ok ? "이메일 인증 성공!" : "유효하지 않은 인증 링크입니다.");
        return "membership/verify_result";
    }
}
