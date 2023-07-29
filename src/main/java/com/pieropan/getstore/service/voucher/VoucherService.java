package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.repository.VoucherRepository;
import com.pieropan.getstore.request.VoucherCadastroRequest;
import com.pieropan.getstore.response.VoucherPorEmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.pieropan.getstore.enums.ValidacaoEnum.NAO_FOI_POSSIVEL_CRIAR_VOUCHER;
import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_NAO_ENCONTRADO_POR_EMAIL;
import static com.pieropan.getstore.mapper.VoucherMapper.INSTANCE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Service
public class VoucherService {

    VoucherRepository repository;

    List<ValidarUsoVoucher> validacoes;

    List<ValidarCriacaoVoucher> validacoesCriacaoVoucher;

    public int utilizarVoucher(String codigoVoucher, String email) {
        Optional<Voucher> voucher = repository.findByCodigoVoucherAndEmail(codigoVoucher, email);
        validacoes.forEach(v -> v.validar(voucher));

        atualizarDataUsoVoucher(voucher.get());
        return voucher.get().getDescontoPercentualFixo();
    }

    void atualizarDataUsoVoucher(Voucher voucher) {
        voucher.setDataUso(LocalDateTime.now());
        repository.save(voucher);
    }

    public List<VoucherPorEmailResponse> obterVoucherPorEmail(String email) {
        List<Voucher> vouchers = repository.findByEmail(email);

        if (vouchers.isEmpty()) {
            throw new ExcecaoCustomizada(VOUCHER_NAO_ENCONTRADO_POR_EMAIL.getDescricao(), BAD_REQUEST.value());
        }
        return INSTANCE.toVoucherPorEmailResponse(vouchers);
    }

    public void criarVoucher(VoucherCadastroRequest voucherCadastroRequest) {
        Optional<Voucher> voucher = repository.findByDestinatario(voucherCadastroRequest.getDestinatario());
        if (voucher.isEmpty()) {
            repository.save(INSTANCE.toVoucher(voucherCadastroRequest));
        } else {

            boolean naoPossoCadastrar = validacoesCriacaoVoucher.stream()
                    .allMatch(v -> v.validar(voucher.get(), voucherCadastroRequest));

            if (naoPossoCadastrar) {
                throw new ExcecaoCustomizada(NAO_FOI_POSSIVEL_CRIAR_VOUCHER.getDescricao(), BAD_REQUEST.value());
            }
            repository.save(INSTANCE.atualizarVoucher(voucherCadastroRequest, voucher.get()));
        }
    }
}