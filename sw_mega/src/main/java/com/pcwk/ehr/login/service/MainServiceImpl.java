package com.pcwk.ehr.login.service;

import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

	@Override
	public String getWelcomeMessage() {
		 return "오늘도 건강한 하루를 HELLMATE와 함께 시작하세요!";
	}

}
