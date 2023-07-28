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
import java.util.stream.Collectors;

import static com.pieropan.getstore.enums.ValidacaoEnum.NAO_FOI_POSSIVEL_CRIAR_VOUCHER;
import static com.pieropan.getstore.enums.ValidacaoEnum.VOUCHER_NAO_ENCONTRADO_POR_EMAIL;
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
        List<Voucher> voucher = repository.findByEmail(email);

        if (voucher.isEmpty()) {
            throw new ExcecaoCustomizada(VOUCHER_NAO_ENCONTRADO_POR_EMAIL.getDescricao(), BAD_REQUEST.value());
        }

        return voucher.stream().map(this::getVoucherPorEmailResponse).collect(Collectors.toList());
    }

    VoucherPorEmailResponse getVoucherPorEmailResponse(Voucher voucher) {
        return new VoucherPorEmailResponse(voucher.getCodigoVoucher(), voucher.getOfertaEspecial());
    }

    public void criarVoucher(VoucherCadastroRequest voucherCadastroRequest) {
        Optional<Voucher> voucher = repository.findByDestinatario(voucherCadastroRequest.getDestinatario());
        if (voucher.isEmpty()) {
            repository.save(getVoucher(voucherCadastroRequest));
        } else {

            boolean naoPossoCadastrar = validacoesCriacaoVoucher.stream()
                    .allMatch(v -> v.validar(voucher.get(), voucherCadastroRequest));

            if (naoPossoCadastrar) {
                throw new ExcecaoCustomizada(NAO_FOI_POSSIVEL_CRIAR_VOUCHER.getDescricao(), BAD_REQUEST.value());
            }
            atualizarVoucher(voucher.get(),voucherCadastroRequest.getOfertaEspecial(),voucherCadastroRequest.getDataValidade());
        }
    }

    void atualizarVoucher(Voucher voucher, String novaOfertaEspecial, LocalDateTime novaValidade) {
        String codigoUnico = gerarCodigoUnico(voucher.getDestinatario(), novaOfertaEspecial, novaValidade.toString());
        voucher.setCodigoUnico(codigoUnico);
        voucher.setOfertaEspecial(novaOfertaEspecial);
        voucher.setDataValidade(novaValidade);

        repository.save(voucher);
    }

    Voucher getVoucher(VoucherCadastroRequest voucherCadastroRequest) {
        String destinatario = voucherCadastroRequest.getDestinatario();
        String ofertaEspecial = voucherCadastroRequest.getOfertaEspecial();
        LocalDateTime validade = voucherCadastroRequest.getDataValidade();

        return new Voucher(null, destinatario,
                voucherCadastroRequest.getNome(),
                voucherCadastroRequest.getEmail(),
                ofertaEspecial,
                voucherCadastroRequest.getDescontoPercentualFixo(),
                voucherCadastroRequest.getCodigoVoucher(),
                gerarCodigoUnico(destinatario, ofertaEspecial, validade.toString()),
                validade, null);
    }

    String gerarCodigoUnico(String destinatario, String ofertaEspecial, String validade) {
        return new StringBuilder(destinatario).append(ofertaEspecial).append(validade).toString();
    }
}