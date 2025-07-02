package com.pcwk.ehr.membership.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /*───────────────────────────────────────────────────────────*/
    /* Constructor                                               */
    /*───────────────────────────────────────────────────────────*/
    public MembershipController() {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ MembershipController()                                   │");
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
    public String doSave(MembershipDTO param, Model model) throws SQLException {
        String redirect = "redirect:/membership/doRetrieve.do";

        log.debug("┌───────────────────────────┐");
        log.debug("│ *doSave()* param: {}      │", param);
        log.debug("└───────────────────────────┘");

        int flag = membershipService.save(param);
        model.addAttribute("msg", (flag == 1) ? "등록 성공" : "등록 실패");

        return redirect;
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
    /* 5. 수정 화면 */
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
}
