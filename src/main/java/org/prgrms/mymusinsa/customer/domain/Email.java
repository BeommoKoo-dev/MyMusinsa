package org.prgrms.mymusinsa.customer.domain;

import lombok.Getter;

@Getter
public class Email {

    private final String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
