package org.prgrms.mymusinsa.customer.dto;

import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.domain.Email;

public record CustomerLoginRequestDTO(
    @jakarta.validation.constraints.Email(message = "유효하지 않은 메일 주소입니다.")
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
