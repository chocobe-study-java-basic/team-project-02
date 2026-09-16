package com.budzet.domain.room.repository;

import com.budzet.domain.room.entity.UserRoomConnection;
import com.budzet.domain.room.entity.UserRoomConnectionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoomConnectionRepository
        extends JpaRepository<UserRoomConnection, UserRoomConnectionId> {

    Optional<UserRoomConnection> findByUser_IdAndRoom_Id(
            Long userId,
            Long roomId
    );

    List<UserRoomConnection> findAllByRoom_Id(Long roomId);
}