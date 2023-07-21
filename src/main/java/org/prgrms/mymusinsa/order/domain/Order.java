package org.prgrms.mymusinsa.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.prgrms.mymusinsa.order.dto.OrderResponseDTO;
import org.prgrms.mymusinsa.order.dto.OrderUpdateRequestDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private String address;
    private String postcode;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderResponseDTO toResponseDTO() {
        return new OrderResponseDTO(
            orderId,
            customerId,
            address,
            postcode,
            orderItems,
            orderStatus,
            createdAt,
            updatedAt
        );
    }

    public void update(OrderUpdateRequestDTO orderUpdateRequestDTO) {
        this.orderItems = orderUpdateRequestDTO.orderItems();
        this.address = orderUpdateRequestDTO.address();
        this.postcode = orderUpdateRequestDTO.postcode();
        this.orderStatus = orderUpdateRequestDTO.orderStatus();
        this.updatedAt = LocalDateTime.now();
    }

}
