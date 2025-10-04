package org.example.jwt.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        ProblemDetail errorDetail;

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
        } else if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        } else if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        } else if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        } else if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        } else {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }
}