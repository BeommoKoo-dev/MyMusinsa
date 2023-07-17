package org.prgrms.mymusinsa.order.dto;

import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderUpdateRequestDTO(
    List<OrderItem> orderItems,
    String address,
    String postcode,
    OrderStatus orderStatus
) {

    public Order toOrder() {
        return new Order(
            null,
            null,
            orderItems,
            orderStatus,
            address,
            postcode,
            null,
            LocalDateTime.now()
        );
    }

}
