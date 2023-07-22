package org.prgrms.mymusinsa.order.dto;

import jakarta.validation.constraints.NotBlank;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
    UUID orderId,
    UUID customerId,
    String address,
    String postcode,
    List<OrderItem> orderItems,
    OrderStatus orderStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
