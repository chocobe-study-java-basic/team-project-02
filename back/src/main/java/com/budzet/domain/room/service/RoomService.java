package com.budzet.domain.room.service;

import com.budzet.domain.room.dto.RoomCreateRequest;
import com.budzet.domain.room.dto.RoomCreateResponse;
import com.budzet.domain.room.entity.Room;
import com.budzet.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // TODO: (김영우)
    // TODO: 인증 연동 후 Long userId를 파라미터로 받기
    // TODO: Room과 UserRoomConnection 저장을 하나의 트랜잭션으로 묶을 예정
    @Transactional
    public RoomCreateResponse createRoom(RoomCreateRequest request) {
        Room room = Room.create(
                request.name(),
                request.totalBudget(),
                request.currency()
        );

        // TODO: User 조회하기(영속성 객체 필요)

        Room savedRoom = roomRepository.save(room);
        // TODO: Room 저장 후 UserRoomConnection을 OWNER, joined=true로 함께 저장하기

        return RoomCreateResponse.from(savedRoom);
    }
}
