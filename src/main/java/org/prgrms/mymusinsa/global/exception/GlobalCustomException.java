package org.prgrms.mymusinsa.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalCustomException extends RuntimeException{

    private final ErrorCode errorCode;

}
