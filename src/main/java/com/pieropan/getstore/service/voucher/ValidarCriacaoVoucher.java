package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.request.VoucherCadastroRequest;

public interface ValidarCriacaoVoucher {

    boolean validar(Voucher voucher, VoucherCadastroRequest voucherCadastroRequest) throws RuntimeException;
}