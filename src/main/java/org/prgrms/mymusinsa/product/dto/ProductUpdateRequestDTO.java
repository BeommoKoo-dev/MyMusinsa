package org.prgrms.mymusinsa.product.dto;

import org.prgrms.mymusinsa.product.domain.Category;

public record ProductUpdateRequestDTO(
    String productName,
    Category category,
    long price,
    String description
) {
}
