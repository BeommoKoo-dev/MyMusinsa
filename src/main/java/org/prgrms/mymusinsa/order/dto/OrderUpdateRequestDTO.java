package org.prgrms.mymusinsa.order.dto;

import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.domain.OrderStatus;

import java.util.List;

public record OrderUpdateRequestDTO(
    List<OrderItem> orderItems,
    String address,
    String postcode,
    OrderStatus orderStatus
) {

}
