package com.pcwk.ehr.board.controller;

import java.util.List;

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
import com.pcwk.ehr.board.domain.ReportDTO;
import com.pcwk.ehr.board.service.ReportService;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.ReportMapper;

@Controller
@RequestMapping("/report")
public class ReportController {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	ReportService reportService;

	@Autowired
	ReportMapper reportMapper;

	public ReportController() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *ReportController()*      │");
		log.debug("└───────────────────────────┘");
	}

	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(ReportDTO report) throws RuntimeException {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSave()                   │");
		log.debug("└────────────────────────────┘");
		log.debug("report: {}", report);

		int flag = reportMapper.doSave(report);

		String message = (flag == 1) ? report.getReason() + " 신고가 등록되었습니다." : "신고 등록 실패!";
		MessageDTO messageDTO = new MessageDTO(flag, message);

		String jsonString = new Gson().toJson(messageDTO);
		log.debug("jsonString: {}", jsonString);

		return jsonString;
	}

	/**
	 * 단건 조회
	 */
	@GetMapping("/doSelectOne.do")
	public String doSelectOne(@RequestParam("reportCode") int reportCode, Model model) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doSelectOne()              │");
		log.debug("└────────────────────────────┘");
		log.debug("reportCode: {}", reportCode);

		ReportDTO outVO = reportMapper.doSelectOne(reportCode);
		model.addAttribute("vo", outVO);

		return "report/report_mod"; // JSP 페이지 이름
	}

	/**
	 * 목록 조회
	 */
	@GetMapping("/doRetrieve.do")
	public String doRetrieve(SearchDTO search, Model model) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doRetrieve()               │");
		log.debug("└────────────────────────────┘");
		log.debug("search: {}", search);

		List<ReportDTO> list = reportMapper.doRetrieve(search);
		int totalCnt = reportMapper.getCount();

		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("search", search);

		return "report/report_list"; // JSP 페이지 이름
	}

	/**
	 * 삭제
	 */
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(ReportDTO report) {
		log.debug("┌────────────────────────────┐");
		log.debug("│ doDelete()                 │");
		log.debug("└────────────────────────────┘");
		log.debug("report: {}", report);

		int flag = reportMapper.doDelete(report);
		String message = (flag == 1) ? "삭제 되었습니다." : "삭제 실패!";
		MessageDTO messageDTO = new MessageDTO(flag, message);

		String jsonString = new Gson().toJson(messageDTO);
		log.debug("jsonString: {}", jsonString);

		return jsonString;

	}

}
