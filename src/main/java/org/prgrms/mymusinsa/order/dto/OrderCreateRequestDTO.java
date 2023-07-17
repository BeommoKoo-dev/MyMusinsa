package org.prgrms.mymusinsa.order.dto;

import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderCreateRequestDTO(
    UUID customerId,
    List<OrderItem> orderItems,
    String address,
    String postcode,
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
