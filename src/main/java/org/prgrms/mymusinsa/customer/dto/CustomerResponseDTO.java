package org.prgrms.mymusinsa.customer.dto;

import org.prgrms.mymusinsa.customer.domain.Email;
import org.prgrms.mymusinsa.product.domain.Category;

public record CustomerResponseDTO(
    Email email,
    String name,
    String address,
    Category interestedCategory
) {
}
