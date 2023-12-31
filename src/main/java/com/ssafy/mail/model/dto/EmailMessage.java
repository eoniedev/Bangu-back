package com.ssafy.mail.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessage {
    private String to;
    private String from;
    private String subject;
    private String message;
}