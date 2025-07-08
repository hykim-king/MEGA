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
import com.pcwk.ehr.board.domain.NoticeDTO;
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
	
	public NoticeController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *NoticeController()*      │");
		log.debug("└───────────────────────────┘");
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
	public String doUpdate(NoticeDTO param, HttpServletRequest req) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*              │");
		log.debug("└───────────────────────────┘");		
		log.debug("1. param: {}",param);

		int flag = noticeService.doUpdate(param);
		String message = "";
		if(1==flag) {
			message = "수정 되었습니다.";
		}else {
			message = "수정 실패.";
		}

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
