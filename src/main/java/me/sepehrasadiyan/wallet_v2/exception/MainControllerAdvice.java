package me.sepehrasadiyan.wallet_v2.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;

@ControllerAdvice
@ResponseBody
public class MainControllerAdvice {

    @ExceptionHandler({NoDataFoundException.class})
    public ResponseEntity<ErrorMessageResponse> handelNotFoundRequests(Exception e) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(e.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handelUserNotFoundException(UserNotFoundException e) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(e.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageResponse);
    }


    @ExceptionHandler({AccountBalanceException.class})
    public ResponseEntity<ErrorMessageResponse> handelUnprocessableEntity(AccountBalanceException e) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(e.getLocalizedMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessageResponse);
    }

    @ExceptionHandler({UserStatusException.class})
    public ResponseEntity<ErrorMessageResponse> handelUnprocessableEntity(UserStatusException e) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(e.getLocalizedMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessageResponse);
    }

    @ExceptionHandler({InternalErrorException.class})
    public ResponseEntity<ErrorMessageResponse> handelInternalError(InternalErrorException e) {
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(e.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageResponse);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorMessageResponse> handelValidationRequests(Exception e) {
        String errorMessages = null;
        if (e instanceof ValidationException exception) {
            errorMessages = exception.getMessage();
        } else {
            errorMessages = e.getLocalizedMessage();
        }
        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.builder()
                .description(errorMessages)
                .status(HttpStatus.BAD_REQUEST.toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(Timestamp.from(Instant.now(Clock.systemUTC())))
                .build();
        return ResponseEntity.badRequest().body(errorMessageResponse);
    }
}
