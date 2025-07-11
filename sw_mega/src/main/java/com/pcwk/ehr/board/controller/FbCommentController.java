package com.pcwk.ehr.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.board.domain.FreeBoardDTO;
import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.board.service.FreeBoardCommentService;
import com.pcwk.ehr.board.service.FreeBoardService;
import com.pcwk.ehr.board.service.L_ReactionService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FbCommentMapper;

@Controller
@RequestMapping("/fbComment")
public class FbCommentController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	FbCommentMapper mapper;

	@Autowired
	FreeBoardCommentService freeBoardCommentService;
	
	@Autowired
	L_ReactionService reactionService;
	
	@Autowired
	FreeBoardService freeboardService;
	

	public FbCommentController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *FbCommentController()*   │");
		log.debug("└───────────────────────────┘");
	}
	
	@GetMapping("/doDetail.do")
	public String doDetail(@RequestParam("fbCode") int fbCode, Model model, HttpSession session) {
	    log.debug("┌───────────────────────────┐");
	    log.debug("│ *doDetail()*              │");
	    log.debug("└───────────────────────────┘");

	    String userId = (String) session.getAttribute("userId");
	    if (userId == null || userId.isEmpty()) {
	        userId = "user01"; // 테스트용 기본 사용자
	    }

	    // 1. 게시글 조회
	    FreeBoardDTO param = new FreeBoardDTO();
	    param.setFbCode(fbCode);
	    FreeBoardDTO outVO = freeboardService.doSelectOne(param);
	    model.addAttribute("vo", outVO);

	    // 2. 댓글 목록 조회
	    SearchDTO search = new SearchDTO();
	    search.setSearchWord(String.valueOf(fbCode));
	    search.setPageNo(1); // 기본 페이지 설정
	    search.setPageSize(100); // 충분히 큰 값 설정

	    List<FreeBoardCommentDTO> commentList = freeBoardCommentService.doRetrieve(search);

		
		 // 3. 댓글별 좋아요/싫어요 수 계산 및 주입
	    for (FreeBoardCommentDTO comment : commentList) {
		 int like = reactionService.getCount(new L_ReactionDTO("COMMENT",comment.getCommentedCode(), "L")); 
		 int dislike = reactionService.getCount(new L_ReactionDTO("COMMENT",comment.getCommentedCode(), "D"));
		 comment.setLikeCount(like); 
		 comment.setDislikeCount(dislike); }
		 
		 model.addAttribute("commentList", commentList);
		 
		 // 4. 게시글 좋아요/싫어요 
		 L_ReactionDTO likeParam = new L_ReactionDTO("FreeBoard",fbCode, "L"); 
		 L_ReactionDTO dislikeParam = new L_ReactionDTO("FreeBoard",fbCode, "D"); 
		 model.addAttribute("likeCount",reactionService.getCount(likeParam)); 
		 model.addAttribute("dislikeCount",reactionService.getCount(dislikeParam));
		 
		 // 5. 사용자 반응 여부
		 L_ReactionDTO userReactionParam = new L_ReactionDTO();
		 userReactionParam.setUserId(userId);
		 userReactionParam.setTargetType("NOTICE");
		 userReactionParam.setTargetCode(fbCode); L_ReactionDTO myReaction =
		 reactionService.getUserReaction(userReactionParam);
		

	    model.addAttribute("myReaction", myReaction);

	    return "freeboard/freeboard_detail";
	}
	
	
	/**
	 * 댓글 등록
	 */
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(@RequestBody FreeBoardCommentDTO param) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSave()                   │");
		log.debug("└────────────────────────────┘");

        System.out.println("받은 댓글:" + param);

        int flag = freeBoardCommentService.doSave(param);

        return "{\"flag\":" + flag + "}";
    
	}

	/**
	 * 댓글 목록 조회
	 */
	@GetMapping("/doRetrieve.do")
	public String doRetrieve(SearchDTO search, Model model) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doRetrieve()               │");
		log.debug("└────────────────────────────┘");
		log.debug("search: {}", search);

		List<FreeBoardCommentDTO> list = mapper.doRetrieve(search);
		model.addAttribute("list", list);
		model.addAttribute("search", search);

		return "freeboardComment/freeboard_comment_list"; // JSP 경로
	}

	/**
	 * 댓글 수정
	 */
	@PostMapping(value = "/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(@RequestBody FreeBoardCommentDTO dto) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doUpdate()                 │");
		log.debug("└────────────────────────────┘");
		log.debug("dto: {}", dto);

		int flag = mapper.doUpdate(dto);
		String message = (flag == 1) ? "댓글이 수정되었습니다." : "댓글 수정 실패!";
		MessageDTO messageDTO = new MessageDTO(flag, message);

		return new Gson().toJson(messageDTO);
	}

	/**
	 * 댓글 삭제
	 */
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(FreeBoardCommentDTO dto) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");
		log.debug("dto: {}", dto);

		int flag = mapper.doDelete(dto);
		String message = (flag == 1) ? "댓글이 삭제되었습니다." : "댓글 삭제 실패!";
		MessageDTO messageDTO = new MessageDTO(flag, message);

		return new Gson().toJson(messageDTO);
	}

	/**
	 * 단건 조회 (수정용 페이지 이동)
	 */
	@GetMapping("/doSelectOne.do")
	public String doSelectOne(@RequestParam int fbCommentCode, Model model) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");
		FreeBoardCommentDTO param = new FreeBoardCommentDTO();
	    param.setCommentedCode(fbCommentCode);

	    FreeBoardCommentDTO outVO = mapper.doSelectOne(param);
	    model.addAttribute("vo", outVO);


		return "freeboard/fb_comment_update"; // JSP 경로

	}

}
