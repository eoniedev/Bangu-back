package com.ssafy.room.model.service;

import com.ssafy.room.model.dto.RankDataDto;
import com.ssafy.room.model.dto.RankInfoDto;
import com.ssafy.room.model.mapper.RankMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class RankService {
    private final RankMapper rankMapper;

    public RankService(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    public RankInfoDto rankByCity() {
        List<RankDataDto> rankData = rankMapper.rankingByCity();
        log.debug(rankData.toString());
        RankInfoDto rankInfo = new RankInfoDto();
        rankData.forEach(rankDataDto -> {
            rankInfo.getLabels().add(rankDataDto.getRegion());
            rankInfo.getData().add(rankDataDto.getCount());
        });
        return rankInfo;
    }

    public RankInfoDto rankByFemale() {
        String city = topRankCity();
        List<RankDataDto> rankData = rankMapper.rankingByFemale(city);
        RankInfoDto rankInfo = new RankInfoDto();
        rankData.forEach(rankDataDto -> {
            rankInfo.getLabels().add(rankDataDto.getRegion());
            rankInfo.getData().add(rankDataDto.getCount());
        });
        return rankInfo;
    }

    public RankInfoDto rankByMale() {
        String city = topRankCity();
        List<RankDataDto> rankData = rankMapper.rankingByMale(city);
        RankInfoDto rankInfo = new RankInfoDto();
        rankData.forEach(rankDataDto -> {
            rankInfo.getLabels().add(rankDataDto.getRegion());
            rankInfo.getData().add(rankDataDto.getCount());
        });
        return rankInfo;
    }

    public String topRankCity() {
        return rankMapper.rankingTopCity();
    }
}
