package org.prgrms.mymusinsa.product.dto;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductUpdateRequestDTO(
    String productName,
    Category category,
    long price,
    String description
) {

    public Product toProduct() {
        return new Product(
            null,
            productName,
            null,
            price,
            description,
            null,
            LocalDateTime.now()
        );
    }

}
