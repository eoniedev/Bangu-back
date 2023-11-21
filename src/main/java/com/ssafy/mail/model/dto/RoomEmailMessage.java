package com.ssafy.mail.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomEmailMessage {
    private final String to;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String date;
    private final String carName;
    private final String requirement;
    private final String subject;
    private final UserType userType;
    private static final String image = "<a target=\"_blank\" href=\"https://letstdd.site\">"
            + "<img style=\"max-width:30%; height:auto;\" src =\"https://url.kr/mqafdo\"/></a>";

    @Builder
    private RoomEmailMessage(String to, String name, String phoneNumber, String email, String date, String carName,
                             String requirement, String subject, UserType userType) {
        this.to = to;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
        this.carName = carName;
        this.requirement = requirement;
        this.subject = subject;
        this.userType = userType;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(image);
        sb.append("<p> 메일: " + userId + "</p>");
        sb.append("<p> 메일: " + email + "</p>");
        sb.append("-------------------------------</p>");
        sb.append("<p>" + "보증금: " + deposit + "</p>");
        sb.append("<p>" + "월세: " + monthly + "</p>");
        sb.append("<p>" + "기간: " + startDate + "~" + endDate +"</p>");
        sb.append("<p>" + "세부 사항: " + requirement + "</p>");
        return sb.toString();
    }

}
