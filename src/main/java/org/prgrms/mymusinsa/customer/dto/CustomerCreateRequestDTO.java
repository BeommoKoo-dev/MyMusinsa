package org.prgrms.mymusinsa.customer.dto;

import jakarta.validation.constraints.NotBlank;
import org.prgrms.mymusinsa.customer.domain.Customer;
import org.prgrms.mymusinsa.customer.domain.Email;
import org.prgrms.mymusinsa.product.domain.Category;

import java.util.UUID;

public record CustomerCreateRequestDTO(

    @jakarta.validation.constraints.Email(message = "유효하지 않은 메일 주소입니다.")
    @NotBlank
    String email,

    @NotBlank(message = "입력이 누락되었습니다.")
    String password,

    @NotBlank(message = "입력이 누락되었습니다.")
    String name,

    @NotBlank(message = "입력이 누락되었습니다.")
    String address,

    @NotBlank(message = "입력이 누락되었습니다.")
    String postcode,

    Category interestedCategory) {

    public Customer toCustomer() {
        return new Customer(
            UUID.randomUUID(),
            new Email(email),
            password,
            name,
            address,
            postcode,
            interestedCategory
        );
    }

}
