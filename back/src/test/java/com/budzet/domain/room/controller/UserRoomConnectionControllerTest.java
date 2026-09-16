package com.budzet.domain.room.controller;

import com.budzet.domain.room.entity.Authority;
import com.budzet.domain.room.entity.UserRoomConnection;
import com.budzet.domain.room.service.UserRoomConnectionService;
import com.budzet.global.api.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRoomConnectionControllerTest {

    @Test
    void getAuthority_success() {

        UserRoomConnectionService service =
                mock(UserRoomConnectionService.class);

        UserRoomConnectionController controller =
                new UserRoomConnectionController(service);

        UserRoomConnection connection =
                mock(UserRoomConnection.class);

        when(connection.getAuthority())
                .thenReturn(Authority.MEMBER);

        when(service.getConnection(1L, 2L))
                .thenReturn(connection);

        ApiResponse<String> response =
                controller.getAuthority(1L, 2L);

        assertEquals(200, response.resultCode());
        assertEquals("멤버 권한 조회 성공", response.message());
        assertEquals("MEMBER", response.data());
    }
}