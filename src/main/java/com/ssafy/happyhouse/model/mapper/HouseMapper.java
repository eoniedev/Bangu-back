package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;

@Mapper
public interface HouseMapper {
	List<HouseInfoDto> selectHouseInfo(Map<String, String> info);
	List<HouseDealDto> selectHouseDeal(long aptCode);
	HouseDealDto searchDeal(Long no);
}
