package com.ssafy.mail.controller;

import com.ssafy.mail.model.dto.ApplyInfoDto;
import com.ssafy.mail.model.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    @PostMapping
    public ResponseEntity<?> sendApplyMail(@RequestBody ApplyInfoDto applyInfoDto) throws Exception {
        mailService.sendApplyTransfer(applyInfoDto);
        return ResponseEntity.ok().build();
    }
}
