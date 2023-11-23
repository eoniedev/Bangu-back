package com.ssafy.room.model.mapper;

import com.ssafy.room.model.dto.RankDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    List<RankDto> rankingByCity();
    List<RankDto> rankingByFemale(String city);
    List<RankDto> rankingByMale(String city);
    String rankingTopCity();
}
