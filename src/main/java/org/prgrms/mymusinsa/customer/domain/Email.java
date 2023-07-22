package org.prgrms.mymusinsa.customer.domain;

import lombok.Getter;

@Getter
public class Email {

    @jakarta.validation.constraints.Email(message = "유효하지 않은 메일 주소입니다.")
    private final String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
