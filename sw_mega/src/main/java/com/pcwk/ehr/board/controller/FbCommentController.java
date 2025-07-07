package com.pcwk.ehr.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.pcwk.ehr.board.domain.FreeBoardCommentDTO;
import com.pcwk.ehr.board.domain.NoticeCommentDTO;
import com.pcwk.ehr.board.service.FreeBoardCommentService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.FbCommentMapper;

@Controller
@RequestMapping("/freeboardnoticeComment")
public class FbCommentController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	FbCommentMapper mapper;

	@Autowired
	FreeBoardCommentService freeBoardCommentService;

	public FbCommentController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *FbCommentController()*   │");
		log.debug("└───────────────────────────┘");
	}
	/**
	 * 댓글 등록
	 */
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(FreeBoardCommentDTO dto) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSave()                   │");
		log.debug("└────────────────────────────┘");
		log.debug("dto: {}", dto);

		int flag = mapper.doSave(dto);
		String message = (flag == 1) ? "댓글이 등록되었습니다." : "댓글 등록 실패!";
		MessageDTO messageDTO = new MessageDTO(flag, message);

		return new Gson().toJson(messageDTO);
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

		return "noticeComment/notice_comment_list"; // JSP 경로
	}

	/**
	 * 댓글 수정
	 */
	@PostMapping(value = "/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(FreeBoardCommentDTO dto) {
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
	public String doSelectOne(FreeBoardCommentDTO param, Model model, HttpServletRequest req) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");

		FreeBoardCommentDTO result = mapper.doSelectOne(param);
		model.addAttribute("vo", result);

		return "noticeComment/notice_comment_mod"; // JSP 경로

	}

}
