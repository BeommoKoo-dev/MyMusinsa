package org.prgrms.mymusinsa.order.dto;

import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(
    UUID orderId,
    UUID customerId,
    String address,
    String postcode,
    OrderStatus orderStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static OrderResponseDTO of(Order order) {
        return new OrderResponseDTO(order.getOrderId(),
            order.getCustomerId(),
            order.getAddress(),
            order.getPostcode(),
            order.getOrderStatus(),
            order.getCreatedAt(),
            order.getUpdatedAt()
        );
    }
}
