package com.pieropan.getstore.service.voucher.impl;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.service.voucher.ValidarVoucher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_EXPIRADO;

@Component
public class ValidarValidadeImpl implements ValidarVoucher {

    @Override
    public void validar(Optional<Voucher> voucher) {
        boolean expirou = voucher.get().getDataValidade().isAfter(LocalDateTime.now());
        if (expirou) {
            throw new ExcecaoCustomizada(VOUCHER_EXPIRADO.getDescricao(), HttpStatus.BAD_REQUEST.value());
        }
    }
}