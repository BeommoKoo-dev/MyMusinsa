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
    public static ProductResponseDTO of(Product product) {
        return new ProductResponseDTO(
            product.getProductId(),
            product.getProductName(),
            product.getCategory(),
            product.getPrice(),
            product.getDescription(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}
