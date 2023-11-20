package com.ssafy.room.model.service;

import com.ssafy.room.model.dto.RoomDto;

import java.util.List;
import java.util.Map;

public interface TransferService {
    void registerRoom(RoomDto roomDto) throws Exception;
    List<RoomDto> listRooms() throws Exception;
    List<RoomDto> listRoomsByOptions(Map<String, String> options) throws Exception;
    RoomDto getRoom(long roomId) throws Exception;



}
