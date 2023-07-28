package com.pieropan.getstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcecaoCustomizada extends RuntimeException {

    private int statusCode;

    private String mensagem;

    public ExcecaoCustomizada(String message, int statusCode) {
        super(message);
        this.mensagem = message;
        this.statusCode = statusCode;
    }
}