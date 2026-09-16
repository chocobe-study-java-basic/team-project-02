package com.budzet.domain.room.service;

import com.budzet.domain.room.entity.UserRoomConnection;
import com.budzet.domain.room.repository.UserRoomConnectionRepository;
import com.budzet.global.exception.BusinessException;
import com.budzet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoomConnectionService {

    private final UserRoomConnectionRepository userRoomConnectionRepository;

    public UserRoomConnection getConnection(Long roomId, Long userId) {

        return userRoomConnectionRepository
                .findByUser_IdAndRoom_Id(userId, roomId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}