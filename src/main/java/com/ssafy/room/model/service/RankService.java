package com.ssafy.room.model.service;

import com.ssafy.room.model.dto.RankDto;
import com.ssafy.room.model.mapper.RankMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RankService {
    private final RankMapper rankMapper;

    public RankService(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    public List<RankDto> rankByCity() {
        return rankMapper.rankingByCity();
    }

    public List<RankDto> rankByFemale() {
        String city = topRankCity();
        return rankMapper.rankingByFemale(city);
    }

    public List<RankDto> rankByMale() {
        String city = topRankCity();
        return rankMapper.rankingByMale(city);
    }

    public String topRankCity() {
        return rankMapper.rankingTopCity();
    }
}
