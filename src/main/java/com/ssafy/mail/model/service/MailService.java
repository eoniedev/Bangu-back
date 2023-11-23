package com.ssafy.mail.model.service;

import com.ssafy.mail.model.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendApplyTransfer(EmailMessage emailMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailMessage.getTo());
        simpleMailMessage.setFrom("forbangu@gmail.com");
        simpleMailMessage.setText(emailMessage.getMessage());

        javaMailSender.send(simpleMailMessage);
    }
}
