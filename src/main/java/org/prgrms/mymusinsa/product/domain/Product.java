package org.prgrms.mymusinsa.product.domain;

import lombok.Getter;
import org.prgrms.mymusinsa.product.dto.ProductCreateRequestDTO;
import org.prgrms.mymusinsa.product.dto.ProductUpdateRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Product {

    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Product of(ProductCreateRequestDTO productRequestDTO) {
        return new Product(
            UUID.randomUUID(),
            productRequestDTO.productName(),
            productRequestDTO.category(),
            productRequestDTO.price(),
            productRequestDTO.description(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    public static Product of(UUID productId, ProductUpdateRequestDTO productUpdateRequestDTO) {
        return new Product(
            productId,
            productUpdateRequestDTO.productName(),
            productUpdateRequestDTO.category(),
            productUpdateRequestDTO.price(),
            productUpdateRequestDTO.description(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

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

}
