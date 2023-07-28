package com.pieropan.getstore.service.voucher.impl.uso;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.service.voucher.ValidarUsoVoucher;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_NAO_ENCONTRADO;

@Order(1)
@Component
public class ValidarUsoCodigoAndEmailImpl implements ValidarUsoVoucher {

    @Override
    public void validar(Optional<Voucher> voucher) {
        if (voucher.isEmpty()) {
            throw new ExcecaoCustomizada(VOUCHER_NAO_ENCONTRADO.getDescricao(), HttpStatus.BAD_REQUEST.value());
        }
    }
}