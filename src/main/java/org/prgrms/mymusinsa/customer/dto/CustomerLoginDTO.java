package org.prgrms.mymusinsa.customer.dto;

import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.domain.Email;

public record CustomerLoginDTO(
    String email,
    String password
) {

    public Customer toCustomer() {
        return new Customer(
            new Email(email),
            password
        );
    }

}
