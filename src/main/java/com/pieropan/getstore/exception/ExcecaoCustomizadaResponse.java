package com.pieropan.getstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExcecaoCustomizadaResponse {

    private int statusCode;

    private String mensagem;
}