package com.ilkaygunel.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleJWTSignatureException(SignatureException signatureException) {
        //TODO: Buraya hatanın loglandığı kısım eklenecek...
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid or tampered session. Please log in again.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
