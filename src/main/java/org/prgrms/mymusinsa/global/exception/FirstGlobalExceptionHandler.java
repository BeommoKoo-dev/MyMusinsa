package org.prgrms.mymusinsa.global.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.annotation.Priority;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Priority(1)
@RestControllerAdvice
public class FirstGlobalExceptionHandler {

    @ExceptionHandler(GlobalCustomException.class)
    public ResponseEntity<ErrorResponse> handleGlobalCustomException(final GlobalCustomException e) {
        e.printStackTrace();
        return ResponseEntity
            .status(e.getErrorCode().getStatus().value())
            .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(final MethodArgumentNotValidException e) {
        e.printStackTrace();
        return ResponseEntity
            .status(ErrorCode.VALIDATION_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.VALIDATION_ERROR,
                e.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
        e.printStackTrace();
        return ResponseEntity
            .status(ErrorCode.ILLEGAL_ARGUMENT_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.ILLEGAL_ARGUMENT_ERROR));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(final InvalidFormatException e) {
        e.printStackTrace();
        return ResponseEntity
            .status(ErrorCode.INVALID_FORMAT_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.INVALID_FORMAT_ERROR));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(final DataAccessException e) {
        e.printStackTrace();
        return ResponseEntity
            .status(ErrorCode.DB_UNKNOWN_ERROR.getStatus().value())
            .body(new ErrorResponse(ErrorCode.DB_UNKNOWN_ERROR));
    }

}
