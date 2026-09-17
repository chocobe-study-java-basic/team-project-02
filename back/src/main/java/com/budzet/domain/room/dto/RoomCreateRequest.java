package com.budzet.domain.room.dto;

import com.budzet.domain.room.entity.Currency;
import jakarta.validation.constraints.*;

public record RoomCreateRequest(
        @NotBlank(message = "모임 이름은 필수입니다.")
        @Size(max = 20, message = "모임 이름은 20자 이하여야 합니다.")
        String name,

        @NotNull(message = "총 예산은 필수입니다.")
        @PositiveOrZero(message = "총 예산은 음수가 될 수 없습니다.")
        Long totalBudget,

        @NotNull(message = "통화는 필수입니다.")
        Currency currency
) {
}
