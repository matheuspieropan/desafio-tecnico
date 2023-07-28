package com.pieropan.getstore.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcecaoCustomizada.class)
    public ResponseEntity excecaoCustomizada(ExcecaoCustomizada ex) {
        log.error(ex);
        return ResponseEntity.status(ex.getStatusCode()).body(new ExcecaoCustomizadaResponse(ex.getStatusCode(),ex.getMensagem()));
    }
}