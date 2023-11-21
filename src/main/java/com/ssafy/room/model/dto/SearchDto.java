package com.ssafy.room.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchDto {
    private String startDate;
    private String endDate;
    private int deposit;
    private int monthly;
    private int count;
    private List<Integer> options;
}
