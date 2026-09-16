package com.budzet.domain.user.controller;

import com.budzet.domain.user.dto.UserDto;
import com.budzet.domain.user.dto.UserJoinRequest;
import com.budzet.domain.user.entity.User;
import com.budzet.domain.user.service.UserService;
import com.budzet.global.api.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Test
    @DisplayName("회원 가입 성공")
    void join_success() {

        UserService service =
                mock(UserService.class);

        UserController controller =
                new UserController(service);

        String email = "user1@test.com";
        String password = "password123";
        String name = "user1";

        User user = new User(
                email,
                "encodedPassword",
                name
        );

        when(service.join(email, password, name))
                .thenReturn(user);

        UserJoinRequest request =
                new UserJoinRequest(email, password, name);

        ApiResponse<UserDto> response =
                controller.join(request);

        assertEquals(201, response.resultCode());
        assertEquals("회원가입이 완료되었습니다.", response.message());
        assertEquals(email, response.data().email());
        assertEquals(name, response.data().name());
    }
}