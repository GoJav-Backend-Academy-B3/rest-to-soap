package com.phincon.wls.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phincon.wls.exception.custom.BadRequestException;
import com.phincon.wls.exception.custom.ConnectionTimeoutException;
import com.phincon.wls.exception.custom.NotFoundException;
import com.phincon.wls.model.dto.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        log.warn("Exception: {}", e.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        log.warn("BadRequestException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        log.warn("NotFoundException: {}", e.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(value = ConnectionTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleConnectionTimeoutException(ConnectionTimeoutException e){
        ErrorResponse response = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.value(), e.getMessage(), null);
        log.warn("ConnectionTimeoutException: {}", e.getMessage());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
