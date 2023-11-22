package com.ssafy.room.model.mapper;

import com.ssafy.room.model.dto.RoomDto;
import com.ssafy.room.model.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {

    List<RoomDto> listRooms() throws Exception;

    List<RoomDto> listRoomsByOptions(SearchDto options) throws Exception;

    void registerRoom(RoomDto roomDto) throws Exception;

    void registerFile(RoomDto roomDto) throws Exception;

    RoomDto getRoom(long roomId) throws Exception;

    void deleteFile(long roomId) throws Exception;

    void insertOptions(RoomDto roomDto) throws Exception;
}
