package com.pcwk.ehr.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.login.dao.FindPwdDao;
import com.pcwk.ehr.login.domain.FindPwdDTO;

@Service
public class FindPwdServiceImpl implements FindPwdService {

	@Autowired
    FindPwdDao findPwdDao;

    @Override
    public boolean resetPassword(FindPwdDTO dto) {
        // 임시 비밀번호 생성
        String tempPwd = generateTempPassword();
        dto.setPassword(tempPwd);
        int result = findPwdDao.updatePassword(dto);
        if(result > 0) {
            System.out.println("임시 비밀번호: " + tempPwd); // 실제 환경에서는 메일 발송 등
            return true;
        }
        return false;
    }

    private String generateTempPassword() {
        return Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0,8);
    }

}
