package org.prgrms.mymusinsa.order.dto;

import jakarta.validation.constraints.NotBlank;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.util.List;

public record OrderUpdateRequestDTO(
    @NotBlank(message = "입력이 누락되었습니다.")
    List<OrderItem> orderItems,

    @NotBlank(message = "입력이 누락되었습니다.")
    String address,

    @NotBlank(message = "입력이 누락되었습니다.")
    String postcode,

    @NotBlank(message = "입력이 누락되었습니다.")
    OrderStatus orderStatus
) {
}
