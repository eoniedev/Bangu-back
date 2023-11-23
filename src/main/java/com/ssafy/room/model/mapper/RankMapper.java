package com.ssafy.room.model.mapper;

import com.ssafy.room.model.dto.RankDataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    List<RankDataDto> rankingByCity();
    List<RankDataDto> rankingByFemale(String city);
    List<RankDataDto> rankingByMale(String city);
    String rankingTopCity();
}
