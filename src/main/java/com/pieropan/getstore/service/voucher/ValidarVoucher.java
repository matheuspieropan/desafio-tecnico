package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.entity.Voucher;

import java.util.Optional;

public interface ValidarVoucher {

    void validar(Optional<Voucher> voucher) throws RuntimeException;
}