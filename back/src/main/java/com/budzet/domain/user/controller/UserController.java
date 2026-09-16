package com.budzet.domain.user.controller;

import com.budzet.domain.user.dto.UserDto;
import com.budzet.domain.user.dto.UserJoinRequest;
import com.budzet.domain.user.entity.User;
import com.budzet.domain.user.service.UserService;
import com.budzet.global.api.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public ApiResponse<UserDto> join(
            @RequestBody @Valid UserJoinRequest reqBody
    ){
        User user = userService.join(reqBody.email(), reqBody.password(), reqBody.name());

        return ApiResponse.success(
                HttpStatus.CREATED,
                "회원가입이 완료되었습니다.",
                new UserDto(user)
        );
    }

}
