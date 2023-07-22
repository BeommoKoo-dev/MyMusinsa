package org.prgrms.mymusinsa.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerLoginRequestDTO(
    @jakarta.validation.constraints.Email(message = "유효하지 않은 메일 주소입니다.")
    @NotBlank(message = "입력이 누락되었습니다.")
    String email,

    @NotBlank(message = "입력이 누락되었습니다.")
    String password
) {
}
