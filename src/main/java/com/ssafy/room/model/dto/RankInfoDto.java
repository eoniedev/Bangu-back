package com.ssafy.room.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RankInfoDto {
    private List<String> labels;
    private List<Integer> data;

    public RankInfoDto() {
        labels = new ArrayList<>();
        data = new ArrayList<>();
    }
}
