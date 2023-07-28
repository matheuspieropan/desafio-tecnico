package com.pieropan.getstore.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcecaoCustomizada.class)
    public ResponseEntity excecaoCustomizada(ExcecaoCustomizada ex) {
        log.error(ex);
        return ResponseEntity.status(ex.getStatusCode()).body(new ExcecaoCustomizadaResponse(ex.getStatusCode(), ex.getMensagem()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsException(BadCredentialsException ex) {
        log.error(ex);
        return ResponseEntity.badRequest().body(new ExcecaoCustomizadaResponse(UNAUTHORIZED.value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception ex) {
        log.error(ex);
        return ResponseEntity.badRequest().body(new ExcecaoCustomizadaResponse(BAD_REQUEST.value(), ex.getMessage()));
    }
}