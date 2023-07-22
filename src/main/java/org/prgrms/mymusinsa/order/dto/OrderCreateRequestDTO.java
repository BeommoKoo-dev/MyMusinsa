package org.prgrms.mymusinsa.order.dto;

import jakarta.validation.constraints.NotBlank;
import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderCreateRequestDTO(
    @NotBlank(message = "입력이 누락되었습니다.")
    UUID customerId,

    @NotBlank(message = "입력이 누락되었습니다.")
    List<OrderItem> orderItems,

    @NotBlank(message = "입력이 누락되었습니다.")
    String address,

    @NotBlank(message = "입력이 누락되었습니다.")
    String postcode,

    @NotBlank(message = "입력이 누락되었습니다.")
    OrderStatus orderStatus
) {

    public Order toOrder() {
        return new Order(
            UUID.randomUUID(),
            customerId,
            orderItems,
            orderStatus,
            address,
            postcode,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

}
