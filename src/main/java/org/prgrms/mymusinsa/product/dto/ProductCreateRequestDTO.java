package org.prgrms.mymusinsa.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductCreateRequestDTO(

    @NotBlank(message = "입력이 누락되었습니다.")
    String productName,

    Category category,

    @NotNull(message = "입력이 누락되었습니다.")
    Long price,

    @NotBlank(message = "입력이 누락되었습니다.")
    String description
) {

    public Product toProduct() {
        return new Product(
            UUID.randomUUID(),
            productName,
            category,
            price,
            description,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

}
