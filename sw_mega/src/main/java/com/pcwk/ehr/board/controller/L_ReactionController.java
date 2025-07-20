package com.pcwk.ehr.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/reaction")
public class L_ReactionController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	L_ReactionService reactionService;
    
	@Autowired
    L_ReactionMapper mapper;

	
	public L_ReactionController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *L_ReactionController()   │");
		log.debug("└───────────────────────────┘");
		
	}
	
	
	@GetMapping(value = "/getUserReaction.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public L_ReactionDTO getUserReaction(
	        @RequestParam("targetType") String targetType,
	        @RequestParam("targetCode") int targetCode,
	        HttpServletRequest req) {

	    log.debug("┌────────────────────────┐");
	    log.debug("│ getUserReaction()      │");
	    log.debug("└────────────────────────┘");

	    String userId = (String) req.getSession().getAttribute("userId");
	    if (userId == null) {
	        userId = "user01"; // 테스트용	
	    }

	    L_ReactionDTO param = new L_ReactionDTO();
	    param.setUserId(userId);
	    param.setTargetType(targetType);
	    param.setTargetCode(targetCode);

	    return reactionService.getUserReaction(param); // null 이면 아직 반응 없음
	}
	
	
	
	@PostMapping(value = "/doToggleReaction.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doToggleReaction(@RequestBody L_ReactionDTO reactionDTO, HttpServletRequest req) {
        log.debug("┌──────────────────────┐");
        log.debug("│ doToggleReaction()   │");
        log.debug("└──────────────────────┘");
        log.debug("toggleReaction param: {}", reactionDTO);

		
		String userId = (String) req.getSession().getAttribute("userId");
	    if (userId == null) {
	        userId = "user01"; // 테스트용
	    }
	    reactionDTO.setUserId(userId);
	

	    int flag = reactionService.toggleReaction(reactionDTO);

	    // 최신 좋아요/싫어요 개수 조회
	    L_ReactionDTO likeParam = new L_ReactionDTO();
	    likeParam.setTargetCode(reactionDTO.getTargetCode());
	    likeParam.setTargetType(reactionDTO.getTargetType());
	    likeParam.setReactionType("L");
	    int likeCount = reactionService.getCount(likeParam);

	    L_ReactionDTO dislikeParam = new L_ReactionDTO();
	    dislikeParam.setTargetCode(reactionDTO.getTargetCode());
	    dislikeParam.setTargetType(reactionDTO.getTargetType());
	    dislikeParam.setReactionType("D");
	    int dislikeCount = reactionService.getCount(dislikeParam);

	    Map<String, Object> result = new HashMap<>();
	    result.put("flag", flag);
	    result.put("likeCount", likeCount);
	    result.put("dislikeCount", dislikeCount);
	    result.put("message", "처리 완료");

	    return result;
	}
	
	
	@PostMapping(value = "/doLike.do", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doLike(@RequestBody L_ReactionDTO dto) {
	    log.debug("▶ doLike(): {}", dto);
	    
	    dto.setReactionType("L"); // 좋아요
	    mapper.doSave(dto);

	    int likeCount = mapper.getCount(dto);

	    Map<String, Object> res = new HashMap<>();
	    res.put("likeCount", likeCount);	    
	    return res;
	}

	@PostMapping(value = "/doDislike.do", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doDislike(@RequestBody L_ReactionDTO dto) {
	    log.debug("▶ doDislike(): {}", dto);
	    
	    dto.setReactionType("D"); // 싫어요
	    mapper.doSave(dto);

	    int dislikeCount = mapper.getCount(dto);

	    Map<String, Object> res = new HashMap<>();
	    res.put("dislikeCount", dislikeCount);
	    return res;
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
