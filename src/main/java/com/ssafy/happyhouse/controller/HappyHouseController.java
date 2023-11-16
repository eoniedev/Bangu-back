package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;

@RestController
@RequestMapping("/house")	// ${root}/house?~
public class HappyHouseController {
	
	HouseServiceImpl houseService;
	Logger logger = LoggerFactory.getLogger(HappyHouseController.class);
	
	public HappyHouseController(HouseServiceImpl houseService) {
		this.houseService = houseService;
	}
	
	@GetMapping("/deal")
	public ResponseEntity<?> searchDeal(@RequestParam Long no) {
		logger.info("-----get searchDeal");
		HouseDealDto housDealDto = houseService.searchDeal(no);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(housDealDto);
	}
	
	@GetMapping("/search-dong")
	public ResponseEntity<?> searchByDong(@RequestParam Map<String,String> info) throws SQLException {
		logger.info("-----get searchByDong");
		List<HouseInfoDto> houseInfoDtoList = houseService.searchByStringAddress(info);
	
		if(houseInfoDtoList == null) {
			ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(houseInfoDtoList);

	}
	
	@GetMapping("/search-apt")
	public ResponseEntity<?> searchByApt(@RequestParam Long aptCode) throws SQLException {
		logger.info("-----get searchByApt");
		List<HouseDealDto> aptDealDtoList = houseService.searchAptDealByAptCode(aptCode);
		if(aptDealDtoList == null) {
			ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(aptDealDtoList);
	}
	
}
