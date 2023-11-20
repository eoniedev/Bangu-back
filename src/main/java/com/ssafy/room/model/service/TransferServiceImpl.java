package com.ssafy.room.model.service;

import com.ssafy.board.model.FileInfoDto;
import com.ssafy.room.model.dto.RoomDto;
import com.ssafy.room.model.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService{
    private final RoomMapper roomMapper;

    public TransferServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }


    @Override
    public void registerRoom(RoomDto roomDto) throws Exception {
        roomMapper.registerRoom(roomDto);
        List<FileInfoDto> fileInfos = roomDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            roomMapper.registerFile(roomDto);
        }
    }

    @Override
    public List<RoomDto> listRooms() throws Exception {
        return roomMapper.listRooms();
    }

    @Override
    public List<RoomDto> listRoomsByOptions(Map<String, String> options) throws Exception {
        return roomMapper.listRoomsByOptions(options);
    }

    @Override
    public RoomDto getRoom(long roomId) throws Exception {
        return roomMapper.getRoom(roomId);
    }
}
