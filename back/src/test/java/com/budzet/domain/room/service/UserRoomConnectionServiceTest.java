package com.budzet.domain.room.service;

import com.budzet.domain.room.entity.UserRoomConnection;
import com.budzet.domain.room.repository.UserRoomConnectionRepository;
import com.budzet.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.budzet.domain.room.entity.UserRoomConnection;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoomConnectionServiceTest {

    //가짜repo 생성
    @Mock
    private UserRoomConnectionRepository userRoomConnectionRepository;

    //Mockito가 가짜repo -> Service에 삽입
    @InjectMocks
    private UserRoomConnectionService userRoomConnectionService;

    @Test // 멤버 객체 확인
    void getConnection() {

        UserRoomConnection connection = mock(UserRoomConnection.class);

        when(userRoomConnectionRepository
                .findByUser_IdAndRoom_Id(1L, 1L))
                .thenReturn(Optional.of(connection));

        UserRoomConnection result =
                userRoomConnectionService.getConnection(1L, 1L);

        assertSame(connection, result);

    }

    @Test //존재하지 않는 멤버 예외처리
    void getConnection_memberNotFound() {

        when(userRoomConnectionRepository
                .findByUser_IdAndRoom_Id(1L, 1L))
                .thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> userRoomConnectionService.getConnection(1L, 1L)
        );
    }
}
