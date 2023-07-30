package com.pieropan.getstore.service.voucher.impl.uso;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.service.voucher.ValidarUsoVoucher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_EXPIRADO;

@Component
public class ValidarExpiracaoImpl implements ValidarUsoVoucher {

    @Override
    public void validar(Optional<Voucher> voucher) {
        boolean expirou = voucher.get().getDataValidade().isAfter(LocalDateTime.now());
        if (expirou) {
            throw new ExcecaoCustomizada(VOUCHER_EXPIRADO.getDescricao(), HttpStatus.BAD_REQUEST.value());
        }
    }
}