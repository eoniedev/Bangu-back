package com.ssafy.mail.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void send(Long appointmentId, Long testerId) {
        PostInfo postInfo = postRepository.findPostInfoByAppointmentId(appointmentId)
                .orElseThrow(PostNotFoundException::new);
        EmailMessage ownerEmailMessage = generateEmailMessage(postInfo, testerId, postInfo.getUserId(),
                UserType.OWNER);
        EmailMessage testerEmailMessage = generateEmailMessage(postInfo, postInfo.getUserId(), testerId,
                UserType.TESTER);

        sendMimeMail(ownerEmailMessage);
        sendMimeMail(testerEmailMessage);
    }

    private void sendMimeMail(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessage.setText(emailMessage.getMessage(), "utf-8", "html");
            mimeMessage.addRecipients(Message.RecipientType.TO, emailMessage.getTo());
            mimeMessage.setSubject(emailMessage.getSubject());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public EmailMessage generateEmailMessage(PostInfo postInfo, Long FromId, Long toId, UserType userType) {
        String toEmail = userRepository.findEmailById(toId)
                .orElseThrow(EmailNotFoundException::new);
        User fromUser = userRepository.findById(FromId)
                .orElseThrow(UserNotFoundException::new);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(toEmail)
                .subject(userType.getSubject())
                .carName(postInfo.getCarName())
                .date(postInfo.getDate())
                .requirement(postInfo.getRequirement())
                .email(fromUser.getEmail())
                .name(fromUser.getUserName())
                .userType(userType)
                .phoneNumber(fromUser.getPhoneNumber())
                .build();

        log.info("> email : {} -> {}", fromUser.getEmail(), toEmail);
        return emailMessage;
    }
}
