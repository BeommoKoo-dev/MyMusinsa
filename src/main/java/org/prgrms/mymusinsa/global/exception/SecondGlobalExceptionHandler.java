package org.prgrms.mymusinsa.global.exception;

import jakarta.annotation.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Priority(2)
@RestControllerAdvice
public class SecondGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
            .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}
