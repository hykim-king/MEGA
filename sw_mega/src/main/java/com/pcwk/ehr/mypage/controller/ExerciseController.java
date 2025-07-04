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
import com.pcwk.ehr.cmn.PcwkString;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mypage.domain.ExerciseDTO;
import com.pcwk.ehr.mypage.domain.FoodDTO;
import com.pcwk.ehr.mypage.service.ExerciseService;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ExerciseService exerciseService;
	
	public ExerciseController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ ExerciseController()                  │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	
	
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(ExerciseDTO param) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doDelete()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
	    log.debug("1. param: {}", param);

	    int flag = exerciseService.doDelete(param);

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
	public String doUpdate(ExerciseDTO param) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doUpdate()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param: {}", param);
		
		 int flag = exerciseService.doUpdate(param);
		 String message = "";
		 if(1== flag) {
			 message = "수정 되었습니다.";
		 }else {
			 message = "수정 실패.";
		 }
	
		 return new Gson().toJson(new MessageDTO(flag, message));
		
	}
	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8" )
	@ResponseBody
	public String doSave(ExerciseDTO param) {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSave()                              │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param:{}", param);
		
		int flag = exerciseService.doSave(param);
		String message = "";
		
		if(1 == flag) {
			message = param.getExerciseName()+" 등록되었습니다.";
		}else {
			message = param.getExerciseName()+" 등록 실패 했습니다.";
		}
		
		MessageDTO messageDTO = new MessageDTO(flag, message);
		jsonString = new Gson().toJson(messageDTO);
		log.debug("2. jsonString: {}", jsonString);
		
		return jsonString;
	}
	
	
	@GetMapping(value = "/doRetrieve.do")
	public String doRetrieve(SearchDTO param, Model model){
		String viewName = "/exercise/exercise_list";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doRetrieve()                          │");
		log.debug("└───────────────────────────────────────┘");
		
		 // 1. null 방지 처리 (기본값 세팅)
	    int pageNo = PcwkString.nvlZero(param.getPageNo(), 1);
	    int pageSize = PcwkString.nvlZero(param.getPageSize(), 10);
	    String searchDiv = PcwkString.nullToEmpty(param.getSearchDiv());
	    String searchWord = PcwkString.nullToEmpty(param.getSearchWord());

	    // 2. 다시 DTO에 세팅
	    param.setPageNo(pageNo);
	    param.setPageSize(pageSize);
	    param.setSearchDiv(searchDiv);
	    param.setSearchWord(searchWord);

	    log.debug("param: {}", param);

	    // 3. 서비스 호출
	    List<ExerciseDTO> list = exerciseService.doRetrieve(param);
	    model.addAttribute("list", list);

	    // 4. total count 추출 (cross join 결과에서)
	    int totalCnt = 0;
	    if (list != null && !list.isEmpty()) {
	    	ExerciseDTO totalVO = list.get(0);
	        totalCnt = totalVO.getTotalCnt();
	    }
	    model.addAttribute("totalCnt", totalCnt);

	    return viewName;
	
	}
	
	
	@GetMapping(value = "/doSelectOne.do")
	public String doSelectOne(ExerciseDTO param, Model model) {
		String viewName = "/exercise/exercise_mod";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSelectOne()                         │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("1. param: {}", param);
		
		ExerciseDTO vo = exerciseService.doSelectOne(param);
		log.debug("2. vo: {}", vo);
		model.addAttribute("vo", vo);
		
		return viewName;
	}
	
}
