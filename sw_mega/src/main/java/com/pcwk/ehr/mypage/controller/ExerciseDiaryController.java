package com.pcwk.ehr.mypage.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.ExerciseSummaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryDTO;
import com.pcwk.ehr.mypage.domain.FoodDiaryOutDTO;
import com.pcwk.ehr.mypage.domain.NutritionSummaryDTO;
import com.pcwk.ehr.mypage.service.ExerciseDiaryService;

@Controller
@RequestMapping("/exerciseDiary")
public class ExerciseDiaryController {
	
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ExerciseDiaryService exerciseDiaryService;
	
	public ExerciseDiaryController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ ExerciseDiaryController()             │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(ExerciseDiaryDTO param) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doDelete()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
	    log.debug("1. param: {}", param);

	    int flag = exerciseDiaryService.doDelete(param);

	    String message = "";
	    if (1 == flag) {
	        message = "삭제 되었습니다.";
	    } else {
	        message = "삭제 실패!!";
	    }

	    jsonString = new Gson().toJson(new MessageDTO(flag, message));
	    log.debug("2. jsonString: {}", jsonString);
	    return jsonString;
	}
	
	
	@PostMapping(value = "/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(ExerciseDiaryDTO param) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doUpdate()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param: {}", param);
		
		 int flag = exerciseDiaryService.doUpdate(param);
		 String message = "";
		 if(1== flag) {
			 message = "수정 되었습니다.";
		 }else {
			 message = "수정 실패.";
		 }
	
		 return new Gson().toJson(new MessageDTO(flag, message));
		
	}
	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(ExerciseDiaryDTO param) {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSave()                              │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param:{}", param);
		
		int flag = exerciseDiaryService.doSave(param);
		String message = "";
		
		if(1 == flag) {
			message = "운동 일지가 등록되었습니다.";
		}else {
			message = "운동 일지 등록이 실패했습니다.";
		}
		
		MessageDTO messageDTO = new MessageDTO(flag, message);
		jsonString = new Gson().toJson(messageDTO);
		log.debug("2. jsonString: {}", jsonString);
		
		return jsonString;
	}
	
	
	@GetMapping(value = "/doRetrieve.do")
	public String doRetrieve(ExerciseDiaryDTO param, Model model) {
		String viewName = "/exerciseDiary/exerciseDiary_list";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doRetrieve()                          │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("param: {}", param);
		
	    List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) exerciseDiaryService.doRetrieve(param);
	    model.addAttribute("list", list);
	    log.debug("2. list: {}", list);
	    
	    return viewName;
	}
	
	@GetMapping(value = "/getDailySummary.do")
	public String getDailySummary(ExerciseDiaryDTO param, Model model) {
		String viewName = "/exerciseDiary/exerciseDiary_list";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ getDailySummary()                     │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("param: {}", param);
		
		ExerciseSummaryDTO vo = exerciseDiaryService.getDailySummary(param);
	    model.addAttribute("vo", vo);
	    log.debug("2. vo: {}", vo);
		
		return viewName;
	}
}
