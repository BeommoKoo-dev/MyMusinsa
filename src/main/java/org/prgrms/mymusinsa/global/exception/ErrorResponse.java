package org.prgrms.mymusinsa.global.exception;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String errorName;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorName = errorCode.name();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorName = errorCode.name();
        this.code = errorCode.getCode();
        this.message = message;
    }

}
