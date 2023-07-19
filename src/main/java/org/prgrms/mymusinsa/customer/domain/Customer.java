package org.prgrms.mymusinsa.customer.domain;

import lombok.Getter;
import lombok.Setter;
import org.prgrms.mymusinsa.product.domain.Category;

import java.util.UUID;

@Getter
@Setter
public class Customer {

    private final UUID customerId;
    private Email email;
    private String password;
    private String name;
    private String address;
    private Category interestedCategory;

    public Customer(Email email, String password) {
        this.customerId = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public Customer(UUID customerId, Email email, String password, String name, String address, Category interestedCategory) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.interestedCategory = interestedCategory;
    }

}
