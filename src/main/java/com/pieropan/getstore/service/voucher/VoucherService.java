package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.repository.VoucherRepository;
import com.pieropan.getstore.response.VoucherPorEmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_NAO_ENCONTRADO_POR_EMAIL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Service
public class VoucherService {

    VoucherRepository repository;

    List<ValidarVoucher> validacoes;

    public int utilizarVoucher(String codigoVoucher, String email) {
        Optional<Voucher> voucher = repository.findByCodigoVoucherAndEmail(codigoVoucher, email);
        validacoes.forEach(v -> v.validar(voucher));
        atualizarVoucher(voucher.get());

        return voucher.get().getDescontoPercentualFixo();
    }

    void atualizarVoucher(Voucher voucher) {
        voucher.setDataUso(LocalDateTime.now());
        repository.save(voucher);
    }

    public List<VoucherPorEmailResponse> obterVoucherPorEmail(String email) {
        List<Voucher> voucher = repository.findByEmail(email);

        if (voucher.isEmpty()) {
            throw new ExcecaoCustomizada(VOUCHER_NAO_ENCONTRADO_POR_EMAIL.getDescricao(), BAD_REQUEST.value());
        }

       return voucher.stream().map(this::getVoucherPorEmailResponse).collect(Collectors.toList());
    }

    VoucherPorEmailResponse getVoucherPorEmailResponse(Voucher voucher) {
        return new VoucherPorEmailResponse(voucher.getCodigoVoucher(), voucher.getOfertaEspecial());
    }
}