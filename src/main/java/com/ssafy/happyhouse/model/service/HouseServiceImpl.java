package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {
	
	HouseMapper mapper;
	
	public HouseServiceImpl(HouseMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<HouseInfoDto> searchByStringAddress(Map<String, String> info) throws SQLException {
		List<HouseInfoDto> houseInfoDtoList = mapper.selectHouseInfo(info);
		return houseInfoDtoList;
	}

	// < -------------- 아파트 상세 조회 -------------- >
	public List<HouseDealDto> searchAptDealByAptCode(Long aptCode) throws SQLException {
		List<HouseDealDto> aptDealDtoList = mapper.selectHouseDeal(aptCode);
		
		return aptDealDtoList;
	}

	public HouseDealDto searchDeal(Long no) {
		// TODO Auto-generated method stub
		return mapper.searchDeal(no);
	}
	
	// <--------------- Functions for readability --------------->
	
}
