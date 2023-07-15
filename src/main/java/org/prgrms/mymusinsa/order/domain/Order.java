package org.prgrms.mymusinsa.order.domain;

import lombok.Getter;
import org.prgrms.mymusinsa.customer.domain.Email;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Order {

    private final UUID OrderId;
    private final Email email;

    private String address;
    private String postcode;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, String address, String postcode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        OrderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setAddress(String address) {
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();
    }

}
