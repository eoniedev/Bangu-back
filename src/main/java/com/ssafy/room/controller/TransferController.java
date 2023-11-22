package com.ssafy.room.controller;

import com.ssafy.room.model.dto.RoomDto;
import com.ssafy.room.model.dto.SearchDto;
import com.ssafy.room.model.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Slf4j
public class TransferController {               //TODO: Exception handling
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoomDto>> listRooms() throws Exception {
        List<RoomDto> rooms = transferService.listRooms();
        return ResponseEntity.ok(rooms);
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody RoomDto roomDto) throws Exception {
        transferService.registerRoom(roomDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("list/option")
    public ResponseEntity<List<RoomDto>> listRoomsByOptions(@RequestBody SearchDto searchOption) throws Exception {
        List<RoomDto> rooms = transferService.listRoomsByOptions(searchOption);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable long roomId) throws Exception {
        RoomDto room = transferService.getRoom(roomId);
        return ResponseEntity.ok(room);
    }
}
