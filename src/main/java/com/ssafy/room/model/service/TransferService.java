package com.ssafy.room.model.service;

import com.ssafy.room.model.dto.RoomDto;
import com.ssafy.room.model.dto.SearchDto;

import java.util.List;

public interface TransferService {
    void registerRoom(RoomDto roomDto) throws Exception;

    List<RoomDto> listRooms() throws Exception;

    List<RoomDto> listRoomsByOptions(SearchDto options) throws Exception;

    RoomDto getRoom(long roomId) throws Exception;


}
