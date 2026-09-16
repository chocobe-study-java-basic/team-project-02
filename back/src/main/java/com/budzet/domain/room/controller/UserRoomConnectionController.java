package com.budzet.domain.room.controller;

import com.budzet.domain.room.dto.MemberResponse;
import com.budzet.domain.room.service.UserRoomConnectionService;
import com.budzet.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class UserRoomConnectionController {

    private final UserRoomConnectionService userRoomConnectionService;

    @GetMapping("/{roomId}/members/{userId}/authority")
    public ApiResponse<String> getAuthority(
            @PathVariable Long roomId,
            @PathVariable Long userId
    ) {
        String authority = userRoomConnectionService
                .getConnection(roomId, userId)
                .getAuthority()
                .name();

        return ApiResponse.success(
                HttpStatus.OK,
                "멤버 권한 조회 성공",
                authority
        );
    }

    @GetMapping("/{roomId}/members")
    public ApiResponse<List<MemberResponse>> getMembers(
            @PathVariable Long roomId
    ) {
        List<MemberResponse> members =
                userRoomConnectionService.getMembers(roomId);

        return ApiResponse.success(
                HttpStatus.OK,
                "멤버 목록 조회 성공",
                members
        );
    }
}