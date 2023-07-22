package org.prgrms.mymusinsa.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.prgrms.mymusinsa.customer.dto.CustomerResponseDTO;
import org.prgrms.mymusinsa.product.domain.Category;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private final UUID customerId;
    private Email email;
    private String password;
    private String name;
    private String address;
    private String postcode;
    private Category interestedCategory;

    public CustomerResponseDTO toCustomerResponseDTO() {
        return new CustomerResponseDTO(
            email,
            name,
            address,
            postcode,
            interestedCategory
        );
    }

}
