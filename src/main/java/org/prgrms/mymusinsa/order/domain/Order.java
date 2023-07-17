package org.prgrms.mymusinsa.order.domain;

import lombok.Getter;
import org.prgrms.mymusinsa.customer.domain.Email;
import org.prgrms.mymusinsa.order.dto.OrderCreateRequestDTO;
import org.prgrms.mymusinsa.order.dto.OrderResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private String address;
    private String postcode;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, UUID customerId, List<OrderItem> orderItems, OrderStatus orderStatus,
                 String address, String postcode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.address = address;
        this.postcode = postcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderResponseDTO toResponseDTO() {
        return new OrderResponseDTO(
            orderId,
            customerId,
            address,
            postcode,
            orderStatus,
            createdAt,
            updatedAt
        );
    }

}
