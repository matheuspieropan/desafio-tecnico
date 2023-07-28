package com.pieropan.getstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ValidacaoEnum {

    VOUCHER_NAO_ENCONTRADO("Não foi possível encontrar este código de voucher associado a este e-mail!"),
    VOUCHER_EXPIRADO("Voucher expirado!"),
    VOUCHER_NAO_ENCONTRADO_POR_EMAIL("Não foi encontrado vouchers para este e-mail!");

    private String descricao;
}