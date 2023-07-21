package org.prgrms.mymusinsa.global.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.zip.DataFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalCustomException.class)
    public ResponseEntity<ErrorResponse> handleGlobalCustomException(final GlobalCustomException e) {
        return ResponseEntity
            .status(e.getErrorCode().getStatus().value())
            .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(final MethodArgumentNotValidException e) {
        return ResponseEntity
            .status(ErrorCode.VALIDATION_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.VALIDATION_ERROR,
                e.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException() {
        return ResponseEntity
            .status(ErrorCode.DB_UNKNOWN_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.DB_UNKNOWN_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException() {
        return ResponseEntity
            .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}
