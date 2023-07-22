package org.prgrms.mymusinsa.product.dto;

import jakarta.validation.constraints.NotBlank;
import org.prgrms.mymusinsa.product.domain.Category;

public record ProductUpdateRequestDTO(
    @NotBlank(message = "입력이 누락되었습니다.")
    String productName,

    @NotBlank(message = "입력이 누락되었습니다.")
    Category category,

    @NotBlank(message = "입력이 누락되었습니다.")
    long price,

    @NotBlank(message = "입력이 누락되었습니다.")
    String description
) {
}
