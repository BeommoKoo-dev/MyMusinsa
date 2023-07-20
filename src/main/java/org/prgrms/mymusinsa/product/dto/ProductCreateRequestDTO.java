package org.prgrms.mymusinsa.product.dto;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductCreateRequestDTO(
    String productName,
    String category,
    long price,
    String description
) {

    public Product toProduct() {
        return new Product(
            UUID.randomUUID(),
            productName,
            Category.valueOf(category),
            price,
            description,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

}
