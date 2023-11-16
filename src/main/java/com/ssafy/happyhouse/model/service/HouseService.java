package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;

public interface HouseService {
	
	List<HouseInfoDto> searchByStringAddress(Map<String, String> info) throws SQLException;
	
	List<HouseDealDto> searchAptDealByAptCode(Long aptCode) throws SQLException;

}
