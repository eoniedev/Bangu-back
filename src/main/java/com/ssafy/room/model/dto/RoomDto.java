package com.ssafy.room.model.dto;

import com.ssafy.board.model.FileInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoomDto {
    private long id;
    private String userId;
    private String lat;
    private String lng;
    private String comment;
    private String subject;
    private String registerTime;
    private String startDate;
    private String endDate;
    private int deposit;
    private int monthly;
    private List<Integer> options;
    private List<FileInfoDto> fileInfos;
    private String dongCode;
    private int gender;
}
