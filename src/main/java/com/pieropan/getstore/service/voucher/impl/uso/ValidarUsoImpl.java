package com.pieropan.getstore.service.voucher.impl.uso;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.service.voucher.ValidarUsoVoucher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_USADO;

@Component
public class ValidarUsoImpl implements ValidarUsoVoucher {

    @Override
    public void validar(Optional<Voucher> voucher) throws RuntimeException {
        if (Objects.nonNull(voucher.get().getDataUso())) {
            throw new ExcecaoCustomizada(VOUCHER_USADO.getDescricao(), HttpStatus.BAD_REQUEST.value());
        }
    }
}