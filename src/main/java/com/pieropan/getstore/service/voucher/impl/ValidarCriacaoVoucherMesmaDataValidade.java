package com.pieropan.getstore.service.voucher.impl;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.request.VoucherCadastroRequest;
import com.pieropan.getstore.service.voucher.ValidarCriacaoVoucher;
import org.springframework.stereotype.Component;

@Component
public class ValidarCriacaoVoucherMesmaDataValidade implements ValidarCriacaoVoucher {

    @Override
    public boolean validar(Voucher voucher, VoucherCadastroRequest voucherCadastroRequest) throws RuntimeException {
        return voucher.getDataValidade().isEqual(voucherCadastroRequest.getDataValidade());
    }
}