package com.pieropan.getstore.service.voucher.impl;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.service.voucher.ValidarVoucher;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_NAO_ENCONTRADO;

@Order(1)
@Component
public class ValidarCodigoAndEmailImpl implements ValidarVoucher {

    @Override
    public void validar(Optional<Voucher> voucher) {
        if (voucher.isEmpty()) {
            throw new ExcecaoCustomizada(VOUCHER_NAO_ENCONTRADO.getDescricao(), HttpStatus.BAD_REQUEST.value());
        }
    }
}