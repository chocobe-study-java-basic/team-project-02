package com.budzet.domain.room.dto;

import com.budzet.domain.room.entity.Authority;
import com.budzet.domain.room.entity.UserRoomConnection;

public record MemberResponse(
        Long userId,
        String name,
        Authority authority
) {

    public static MemberResponse from(UserRoomConnection connection) {
        return new MemberResponse(
                connection.getUser().getId(),
                connection.getUser().getName(),
                connection.getAuthority()
        );
    }
}