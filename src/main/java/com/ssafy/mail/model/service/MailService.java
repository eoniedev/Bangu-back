package com.ssafy.mail.model.service;

import com.ssafy.mail.model.dto.ApplyInfoDto;
import com.ssafy.mail.model.dto.EmailMessage;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.room.model.dto.RoomDto;
import com.ssafy.room.model.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final RoomMapper roomMapper;
    private final MemberMapper memberMapper;

    public MailService(JavaMailSender javaMailSender, RoomMapper roomMapper, MemberMapper memberMapper) {
        this.javaMailSender = javaMailSender;
        this.roomMapper = roomMapper;
        this.memberMapper = memberMapper;
    }
    @Async
    public void sendApplyTransfer(ApplyInfoDto applyInfoDto) throws Exception {
        EmailMessage emailMessage = generateMessage(applyInfoDto);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailMessage.getTo());
        simpleMailMessage.setFrom(emailMessage.getFrom());
        simpleMailMessage.setSubject(emailMessage.getSubject());
        simpleMailMessage.setText(emailMessage.getMessage());

        javaMailSender.send(simpleMailMessage);
    }

    public EmailMessage generateMessage(ApplyInfoDto applyInfoDto) throws Exception {
        RoomDto roomDto = roomMapper.getRoom(applyInfoDto.getRoomId());
        MemberDto targetDto = memberMapper.getMember(roomDto.getUserId());
        MemberDto memberDto = memberMapper.getMember(applyInfoDto.getUserId());

        return EmailMessage.builder()
                .from("forbangu@gmail.com")
                .to(memberDto.getEmailId()+'@'+memberDto.getEmailDomain())
                .subject("[Bangu] 신청한 양도 정보입니다.")
                .message("임차인의 정보입니다: " + targetDto.getEmailId()+'@'+targetDto.getEmailDomain())
                .build();
    }
}
