package com.pcwk.ehr.findid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindIdController {
	 @Autowired
	    FindIdService findIdService;

	    @PostMapping("/findId")
	    public String findId(@ModelAttribute FindIdDTO dto, Model model) {
	        String userId = findIdService.findUserId(dto);
	        if(userId != null) {
	            model.addAttribute("userId", userId);
	            return "findIdResult";
	        } else {
	            model.addAttribute("msg", "일치하는 정보가 없습니다.");
	            return "findId";
	        }
	    }
}
