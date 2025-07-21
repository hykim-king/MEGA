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
import com.pcwk.ehr.board.domain.L_ReactionDTO;
import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.board.service.L_ReactionService;
import com.pcwk.ehr.board.service.NoticeCommentService;
import com.pcwk.ehr.board.service.NoticeService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.PcwkString;
import com.pcwk.ehr.cmn.SearchDTO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	NoticeService noticeService;
	
	@Autowired
	L_ReactionService reactionService; 
	
	@Autowired
	NoticeCommentService noticeCommentService;
	
	public NoticeController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *NoticeController()*      │");
		log.debug("└───────────────────────────┘");
	}
	

	
	@GetMapping("/doUpdateView.do")
	public String doUpdateView(@RequestParam("noCode")int noCode,Model model) {
	    log.debug("┌───────────────────────────┐");
	    log.debug("│ *doUpdateView()*          │");
	    log.debug("└───────────────────────────┘");
	    
	    NoticeDTO inVO = new NoticeDTO();
	    inVO.setNoCode(noCode);
	    
	    NoticeDTO outVO = noticeService.doSelectOne(inVO);
	    
	    model.addAttribute("vo",outVO);
	    return "notice/notice_update"; //수정 화면
	    
	}
		
	
	@GetMapping("/doDetail.do")
	public String doDetail(NoticeDTO param,
	                       Model model,
	                       HttpSession session) {
	    log.debug("┌───────────────────────────┐");
	    log.debug("│ *doDetail()*              │");
	    log.debug("└───────────────────────────┘");

	    String userId = (String) session.getAttribute("userId");
	    if (userId == null || userId.isEmpty()) {
	        userId = "user01"; // 테스트용
	    }

	    // 게시글 상세 조회
	    param.setNoCode(param.getNoCode());
	    NoticeDTO outVO = noticeService.doSelectOne(param);
	    model.addAttribute("outVO", outVO);
	    log.debug("outVO:{}",outVO);
	    
	    // 댓글 목록 조회 추가
	    SearchDTO search = new SearchDTO();
	    search.setSearchWord(String.valueOf(outVO.getNoCode()));

	    List<NoticeCommentDTO> commentList = noticeCommentService.doRetrieve(search);
	    model.addAttribute("commentList", commentList);

	    // 공지사항 좋아요/싫어요 수 조회
	    L_ReactionDTO nLikeParam = new L_ReactionDTO();
	    nLikeParam.setTargetCode(param.getNoCode());
	    nLikeParam.setTargetType("NOTICE");
	    nLikeParam.setReactionType("L");
	    int nLikeCount = reactionService.getCount(nLikeParam);

	    L_ReactionDTO nDislikeParam = new L_ReactionDTO();
	    nDislikeParam.setTargetCode(param.getNoCode());
	    nDislikeParam.setTargetType("NOTICE");
	    nDislikeParam.setReactionType("D");
	    int nDislikeCount = reactionService.getCount(nDislikeParam);
	    
	    // 댓글 좋아요/싫어요 수 조회
	    for (NoticeCommentDTO comment : commentList) {
	    L_ReactionDTO cLikeParam = new L_ReactionDTO();
	    cLikeParam.setTargetCode(comment.getCommentedCode());
	    cLikeParam.setTargetType("COMMENT");
	    cLikeParam.setReactionType("L");
	    int cLikeCount = reactionService.getCount(cLikeParam);

	    L_ReactionDTO cDislikeParam = new L_ReactionDTO();
	    cDislikeParam.setTargetCode(comment.getCommentedCode());
	    cDislikeParam.setTargetType("COMMENT");
	    cDislikeParam.setReactionType("D");
	    int cDislikeCount = reactionService.getCount(cDislikeParam);


	    // 🔥 댓글 객체에 바로 주입!
	    comment.setLikeCount(cLikeCount);
	    comment.setDislikeCount(cDislikeCount);
	    }
	    
	    model.addAttribute("nLikeCount", nLikeCount);
	    model.addAttribute("nDislikeCount", nDislikeCount);
	    model.addAttribute("commentList", commentList);


	    return "notice/notice_detail";
	}

	
	
	//등록화면조회	/board/doSaveView.do	doSaveView()	동기	GET	
	@GetMapping("/doSaveView.do")
	public String doSaveView(@RequestParam(name = "div", defaultValue = "10")String div, Model model) {
		String viewNString = "notice/notice_reg";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSaveView()*            │");
		log.debug("└───────────────────────────┘");	
		log.debug("div: {}",div);
		model.addAttribute("notice_div", div);
		
		log.debug("viewNString: {}",viewNString);
		
		return viewNString;
	
	}
	
	//수정	/board/doUpdate.do	doUpdate(BoardDTO param)	비동기	POST	JSON
	@PostMapping(value = "/doUpdate.do",produces ="text/plain;charset=UTF-8" )
	@ResponseBody
	public String doUpdate(@RequestBody NoticeDTO param, HttpServletRequest req) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*              │");
		log.debug("└───────────────────────────┘");		
		log.debug("1. param: {}",param);

		int flag = noticeService.doUpdate(param);
		String message = (flag == 1)? "수정 되었습니다." : "수정 실패.";
		

		return new Gson().toJson(new MessageDTO(flag, message));
	}
	
	
	//목록	/board/doRetrieve.do	doRetrieve(SearchDTO search)	동기	GET	Model
		@GetMapping(value = "/doRetrieve.do")
		public String doRetrieve(SearchDTO param,Model model) {
			String viewName = "notice/notice_list";
			
			log.debug("┌───────────────────────────┐");
			log.debug("│ *doRetrieve()*            │");
			log.debug("└───────────────────────────┘");	
			
			int pageNo = PcwkString.nvlZero(param.getPageNo(), 1);
			int pageSize = PcwkString.nvlZero(param.getPageSize(), 10);
			
			//게시구분: 공지사항(10)
			
			//검색구분
			String searchDiv = PcwkString.nullToEmpty(param.getSearchDiv());
			//검색어
			String searchWord = PcwkString.nullToEmpty(param.getSearchWord());
			
			log.debug("pageNo:{}",pageNo);
			log.debug("pageSize:{}",pageSize);
			log.debug("searchDiv:{}",searchDiv);
			log.debug("searchWord:{}",searchWord);
			
			param.setPageNo(pageNo);
			param.setPageSize(pageSize);
			param.setSearchDiv(searchDiv);
			param.setSearchWord(searchWord);
			
			log.debug("***param:{}",param);
			List<NoticeDTO> list= noticeService.doRetrieve(param);
			
			model.addAttribute("list", list);
			
			//총글수
			int totalCnt = 0;
			
			if(null != list && list.size()>0) {
				NoticeDTO totalVO =  list.get(0);
				totalCnt = totalVO.getTotalCnt();
			}
			
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("search", param);

			
			return viewName;
		}
	

	@GetMapping(value = "/doSelectOne.do")
	public String doSelectOne(NoticeDTO param, Model model) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSelectOne()*           │");
		log.debug("└───────────────────────────┘");
		log.debug("1. param:{}", param);
		String viewName = "notice/notice_mod";
		
		NoticeDTO outVO = noticeService.doSelectOne(param);
		log.debug("2. outVO:{}", outVO);
		model.addAttribute("vo", outVO);

		return viewName;
	}
	

	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(NoticeDTO param, HttpServletRequest req) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doDelete()*              │");
		log.debug("└───────────────────────────┘");
		log.debug("1. param:{}", param);
		String jsonString = "";
		int flag = noticeService.doDelete(param);

		String message = "";
		if (1 == flag) {
			message = "삭제 되었습니다.";
		} else {
			message = "삭제 실패!";
		}

		jsonString = new Gson().toJson(new MessageDTO(flag, message));
		log.debug("2.jsonString:{}", jsonString);
		return jsonString;
	}

	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(NoticeDTO param) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");
		String jsonString = "";
		log.debug("1. param:{}",param);
		
		int flag = noticeService.doSave(param);
		
		String message ="";
		if(1==flag) {
			message = param.getTitle() + "글이 등록 되었습니다.";
		}else {
			message = param.getTitle() + "등록 실패 했습니다.";
		}
		
		MessageDTO messageDTO = new MessageDTO(flag, message);
		jsonString = new Gson().toJson(messageDTO);
		log.debug("2. jsonString:{}",jsonString);
		
		return jsonString;

	}
	
}
