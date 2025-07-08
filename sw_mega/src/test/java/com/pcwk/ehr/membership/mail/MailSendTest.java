package com.pcwk.ehr.membership.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class MailSendTest {

    @Autowired
    JavaMailSender mailSender;

    @Test
    void sendTestMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("doona0711@gmail.com"); // 수신자 메일 주소
        message.setSubject("테스트 메일입니다");
        message.setText("Mega에서 보낸 테스트 메일 내용입니다.");
        message.setFrom("com0494@naver.com"); // 네이버 계정

        mailSender.send(message);
        System.out.println(">>> 메일 전송 완료!");
    }
}
