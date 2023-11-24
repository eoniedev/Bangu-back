package com.ssafy.mail.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplyInfoDto {
    private String userId;
    private int roomId;
}
