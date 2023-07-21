package org.prgrms.mymusinsa.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "COMMON_001","유효성 검증에 통과하지 못하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_001", "내부 서버 오류입니다."),
    DB_UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "DB_001", "알 수 없는 DB 에러입니다. 다시 시도해 보세요."),
    DB_DATA_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST, "DB_002", "조회에 실패했습니다. 다시 시도해 보세요."),
    LOGIN_ERROR(HttpStatus.BAD_REQUEST, "CUSTOMER_001", "로그인에 실패했습니다. 다시 시도해 보세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
