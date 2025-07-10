package com.pcwk.ehr.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	
	@GetMapping("/doForm.do")
	public String doForm(FoodDTO param, Model model, HttpSession session) {
	    String viewName = "/exercise/exercise_form";

	    log.debug("┌───────────────────────────────────────┐");
	    log.debug("│ doForm() - 등록용 폼 진입                                         │");
	    log.debug("└───────────────────────────────────────┘");
	    log.debug("param: {}", param);

	    //세션에서 userId 꺼내기
	    String userId = (String) session.getAttribute("userId");
	    
	    // 🔐 로그인 여부 판단용: userId null 또는 빈 문자열
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동 일지 등록 시도 차단됨");

	        model.addAttribute("message", "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        model.addAttribute("nextUrl", "/ehr/login/login.do"); // 원하는 경로
	        return "/common/error"; // 또는 에러 안내 페이지
	    }

	    // 정상 진입
	    ExerciseDTO outVO = new ExerciseDTO();
	    outVO.setUserId(userId);

	    model.addAttribute("outVO", outVO);
	    model.addAttribute("mode", "add");

	    return viewName;
	}
	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8" )
	@ResponseBody
	public String doSave(ExerciseDTO param, HttpSession session) {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSave()                              │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("1. param:{}", param);
		
	    String jsonString = "";
	    String message = "";
	    int flag = 0;
	    
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동 추가 시도 차단됨");
	        MessageDTO messageDTO = new MessageDTO(-99, "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        return new Gson().toJson(messageDTO);
	    }
	    
	    param.setUserId(userId); //세션에서 주입

	    try {
	        flag = exerciseService.doSave(param);
	        if (1 == flag) {
	            message = param.getExerciseName() + " 등록되었습니다.";
	        } else {
	            message = param.getExerciseName() + " 등록 실패했습니다.";
	        }
	    } catch (DuplicateKeyException e) {
	        flag = -1;
	        message = "❌ 이미 동일한 운동이 존재합니다.";
	        log.warn("❗ DuplicateKeyException: {}", e.getMessage());
	    } catch (Exception e) {
	        flag = -9;
	        message = "🚨 시스템 에러 발생";
	        log.error("❗ Exception: {}", e.getMessage());
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
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("pageNo", pageNo);

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
		model.addAttribute("param", param);
		
		return viewName;
	}
	
}
