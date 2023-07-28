package com.pieropan.getstore.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoucherCadastroRequest {

    private String destinatario;

    private String nome;

    private String email;

    private String ofertaEspecial;

    private int descontoPercentualFixo;

    private String codigoVoucher;

    private LocalDateTime dataValidade;
}