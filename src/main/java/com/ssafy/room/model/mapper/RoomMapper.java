package com.ssafy.room.model.mapper;

import com.ssafy.room.model.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {

    List<RoomDto> listRooms() throws Exception;

    List<RoomDto> listRoomsByOptions(Map<String, String> options) throws Exception;

    void registerRoom(RoomDto roomDto) throws Exception;

    void registerFile(RoomDto roomDto) throws Exception;

    RoomDto getRoom(long roomId) throws Exception;

    void deleteFile(long roomId) throws Exception;
}
