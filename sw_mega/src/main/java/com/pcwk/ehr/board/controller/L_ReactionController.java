package com.pcwk.ehr.board.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.board.service.L_ReactionService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.L_ReactionMapper;

@Controller
@RequestMapping("/l_reaction")
public class L_ReactionController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	L_ReactionService l_reactionService;
    
	@Autowired
    L_ReactionMapper mapper;

	
	public L_ReactionController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *L_ReactionController()   │");
		log.debug("└───────────────────────────┘");
		
	}
	
	 // 등록: /reaction/doSave.do
    @PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doSave(L_ReactionDTO inVO) {
        log.debug("┌────────────┐");
        log.debug("│ doSave()   │");
        log.debug("└────────────┘");
        log.debug("inVO: {}", inVO);

        int flag = mapper.doSave(inVO);
        String message = (flag == 1) ? "등록 성공" : "등록 실패";
        MessageDTO msg = new MessageDTO(flag, message);

        return new Gson().toJson(msg);
    }

    // 삭제: /reaction/doDelete.do
    @PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doDelete(@RequestParam("reactionCode") int reactionCode) {
        log.debug("┌──────────────┐");
        log.debug("│ doDelete()   │");
        log.debug("└──────────────┘");
        log.debug("reactionCode: {}", reactionCode);

        L_ReactionDTO param = new L_ReactionDTO();
        param.setReactionCode(reactionCode);

        int flag = mapper.doDelete(param);
        String message = (flag == 1) ? "삭제 성공" : "삭제 실패";
        MessageDTO msg = new MessageDTO(flag, message);

        return new Gson().toJson(msg);
    }

    // 목록조회: /reaction/doRetrieve.do
    @GetMapping("/doRetrieve.do")
    public String doRetrieve(SearchDTO search, Model model) {
        log.debug("┌────────────────┐");
        log.debug("│ doRetrieve()   │");
        log.debug("└────────────────┘");
        log.debug("search: {}", search);

        List<L_ReactionDTO> list = mapper.doRetrieve(search);
        model.addAttribute("list", list);
        model.addAttribute("totalCnt", list.size());

        return "reaction/reaction_list";  // → /WEB-INF/views/reaction/reaction_list.jsp
    }

	
	
}
