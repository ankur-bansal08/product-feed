package com.tradedoubler.productfeed.Exception;

import jakarta.xml.bind.JAXBException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse
                                .builder()
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse
                                .builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(value = {EmptyFileException.class})
    public ResponseEntity<ErrorResponse> handleEmptyFileException(EmptyFileException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse
                                .builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(value = {ProductFeedException.class})
    public ResponseEntity<ErrorResponse> handleProductFeedException(ProductFeedException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse
                                .builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(value = {JAXBException.class})
    public ResponseEntity<ErrorResponse> handleJAXBException(JAXBException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse
                                .builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}
