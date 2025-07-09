package com.pcwk.ehr.mypage.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String doDelete(ExerciseDiaryDTO param, HttpSession session) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doDelete()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
	    log.debug("1. param: {}", param);
	    
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동일지  삭제 시도 차단됨");
	        MessageDTO messageDTO = new MessageDTO(-99, "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        return new Gson().toJson(messageDTO);
	    }

	    param.setUserId(userId); //세션에서 주입

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
	public String doUpdate(ExerciseDiaryDTO param, HttpSession session) {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doUpdate()                            │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param: {}", param);
		
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동일지 수정 시도 차단됨");
	        MessageDTO messageDTO = new MessageDTO(-99, "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        return new Gson().toJson(messageDTO);
	    }

	    param.setUserId(userId); //세션에서 주입
		
		 int flag = exerciseDiaryService.doUpdate(param);
		 String message = "";
		 if(1== flag) {
			 message = "수정 되었습니다.";
		 }else {
			 message = "수정 실패.";
		 }
	
		 return new Gson().toJson(new MessageDTO(flag, message));
		
	}
	
	
	@GetMapping("/doForm.do")
	public String doForm(FoodDiaryDTO param, Model model, HttpSession session) {
	    String viewName = "/exerciseDiary/exerciseDiary_form";

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
	        model.addAttribute("nextUrl", "/login/login.do"); // 원하는 경로
	        return "/common/error"; // 또는 에러 안내 페이지
	    }

	    // 🔑 파라미터를 JSP에서 사용 가능하도록 바인딩
	    model.addAttribute("param", param);

	    // 정상 진입
	    ExerciseDiaryDTO outVO = new ExerciseDiaryDTO();
	    outVO.setUserId(userId);
	    outVO.setRegDt(param.getRegDt());

	    model.addAttribute("outVO", outVO);
	    model.addAttribute("mode", "add");

	    return viewName;
	}
	
	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(ExerciseDiaryDTO param, HttpSession session) {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSave()                              │");
		log.debug("└───────────────────────────────────────┘");
		String jsonString = "";
		log.debug("1. param:{}", param);
		
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동일지 추가 시도 차단됨");
	        MessageDTO messageDTO = new MessageDTO(-99, "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        return new Gson().toJson(messageDTO);
	    }

	    param.setUserId(userId); //세션에서 주입
		
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
	
	
	@GetMapping("/selectOneWithJoin.do")
	public String selectOneWithJoin(ExerciseDiaryDTO param, Model model, HttpSession session) {
		String viewName = "/exerciseDiary/exerciseDiary_mod";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doSelectOne()                         │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("param: {}", param);
		
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동 일지 단건 조회 시도 차단됨");
	        model.addAttribute("message", "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        model.addAttribute("nextUrl", "/login/login.do"); // 원하는 경로로 리다이렉트 유도
	        return "/common/error";
	    }
	    
	    param.setUserId(userId); //세션에서 주입
		
		
		ExerciseDiaryOutDTO outVO = exerciseDiaryService.selectOneWithJoin(param);

	    if (null == outVO) {
	        model.addAttribute("message", "해당 운동 일지를 찾을 수 없습니다.");
	        model.addAttribute("nextUrl", "/ehr/exerciseDiary/doRetrieve.do");
	        return "/common/error";
	    }

	    model.addAttribute("outVO", outVO);
	    return viewName; // 👉 수정 전용 JSP로 이동!
	}
	
	
	@GetMapping(value = "/doRetrieve.do")
	public String doRetrieve(ExerciseDiaryDTO param, Model model, HttpSession session) {
		String viewName = "/exerciseDiary/exerciseDiary_list";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ doRetrieve()                          │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("param: {}", param);
		
	    String userId = (String) session.getAttribute("userId");
	    
	    if (userId == null || userId.trim().isEmpty()) {
	        log.warn("▶ 로그인 없이 운동 일지 조회 시도 차단됨");
	        model.addAttribute("message", "로그인이 필요한 기능입니다. 먼저 로그인해 주세요.");
	        model.addAttribute("nextUrl", "/login/login.do"); // 원하는 경로로 리다이렉트 유도
	        return "/common/error";
	    }
	    
	    param.setUserId(userId); //세션에서 주입
		
	    // regDt가 null이거나 빈 문자열이면 오늘 날짜로 설정
	    if (param.getRegDt() == null || param.getRegDt().trim().isEmpty()) {
	        param.setRegDt(LocalDate.now().toString()); // yyyy-MM-dd
	    }
		
		// regDt 값에 시간정보 제거
		if (param.getRegDt() != null && param.getRegDt().contains(" ")) {
			param.setRegDt(param.getRegDt().split(" ")[0]);
		}
		
	    List<ExerciseDiaryOutDTO> list = (List<ExerciseDiaryOutDTO>) exerciseDiaryService.doRetrieve(param);
	    ExerciseSummaryDTO vo = exerciseDiaryService.getDailySummary(param);
	    
	    model.addAttribute("list", list);
	    model.addAttribute("exerciseType", Arrays.asList("유산소", "근력"));
	    model.addAttribute("vo", vo);
	    
	    log.debug("2. list: {}", list);
	    log.debug("3. vo: {}", vo);
	    
	    // 🔹 날짜와 사용자 아이디도 JSP에 넘기자
	    model.addAttribute("regDt", param.getRegDt());
	    
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
