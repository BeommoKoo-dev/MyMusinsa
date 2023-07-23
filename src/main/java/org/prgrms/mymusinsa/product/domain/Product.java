package org.prgrms.mymusinsa.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.prgrms.mymusinsa.product.dto.ProductResponseDTO;
import org.prgrms.mymusinsa.product.dto.ProductUpdateRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Product {

    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private long salesCount;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(UUID productId, String productName, Category category, long price, String description,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductResponseDTO toResponseDTO() {
        return new ProductResponseDTO(
            productId,
            productName,
            category,
            price,
            salesCount,
            description,
            createdAt,
            updatedAt
        );
    }

    public void update(ProductUpdateRequestDTO productUpdateRequestDTO) {
        this.productName = productUpdateRequestDTO.productName();
        this.category = productUpdateRequestDTO.category();
        this.price = productUpdateRequestDTO.price();
        this.description = productUpdateRequestDTO.description();
        this.updatedAt = LocalDateTime.now();
    }

}
