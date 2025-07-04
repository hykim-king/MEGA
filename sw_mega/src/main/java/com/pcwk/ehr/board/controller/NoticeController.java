package com.pcwk.ehr.board.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.NoticeDTO;
import com.pcwk.ehr.board.service.NoticeService;
import com.pcwk.ehr.cmn.MessageDTO;

@Controller
@RequestMapping("/board")
public class NoticeController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	NoticeService noticeService;
	
	public NoticeController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *NoticeController()*      │");
		log.debug("└───────────────────────────┘");
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
