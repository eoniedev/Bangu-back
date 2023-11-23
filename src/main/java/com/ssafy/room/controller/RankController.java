package com.ssafy.room.controller;

import com.ssafy.room.model.dto.RankDto;
import com.ssafy.room.model.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/city")
    public ResponseEntity<?> rankByCity() {
        List<RankDto> rankDtoList = rankService.rankByCity();
        return ResponseEntity.ok(rankDtoList);
    }

    @GetMapping("/female")
    public ResponseEntity<?> rankByFemale() {
        List<RankDto> rankDtoList = rankService.rankByFemale();
        return ResponseEntity.ok(rankDtoList);
    }

    @GetMapping("/male")
    public ResponseEntity<?> rankByMale() {
        List<RankDto> rankDtoList = rankService.rankByMale();
        return ResponseEntity.ok(rankDtoList);
    }
}
