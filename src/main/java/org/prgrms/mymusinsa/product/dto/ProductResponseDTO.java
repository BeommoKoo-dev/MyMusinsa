package org.prgrms.mymusinsa.product.dto;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
    UUID productId,
    String productName,
    Category category,
    long price,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
