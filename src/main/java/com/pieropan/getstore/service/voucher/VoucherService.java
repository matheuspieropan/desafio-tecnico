package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.repository.VoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VoucherService {

    VoucherRepository repository;

    List<ValidarVoucher> validacoes;

    public Object utilizarVoucher(String codigoVoucher, String email) {
        Optional<Voucher> voucher = repository.findByCodigoVoucherAndEmail(codigoVoucher, email);
        validacoes.forEach(v -> v.validar(voucher));

        return null;
    }
}