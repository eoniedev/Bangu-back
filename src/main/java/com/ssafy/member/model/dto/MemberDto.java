package com.ssafy.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

    private String userId;
    private String userName;
    private String userPwd;
    private String emailId;
    private String emailDomain;
    private String joinDate;
    private Gender gender;
    private int genderInt;
}
