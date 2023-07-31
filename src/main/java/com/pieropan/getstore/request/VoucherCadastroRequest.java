package com.pieropan.getstore.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoucherCadastroRequest {

    @Schema(defaultValue = "Destinatário")
    private String destinatario;

    @Schema(defaultValue = "Nome")
    private String nome;

    @Schema(defaultValue = "algumemail@gmail.com")
    private String email;

    @Schema(defaultValue = "Oferta tal tal e tal")
    private String ofertaEspecial;

    @Schema(defaultValue = "10")
    private int descontoPercentualFixo;

    @Schema(defaultValue = "DESCONTAO10")
    private String codigoVoucher;

    @Schema(defaultValue = "2023-12-01T00:00:00Z")
    private LocalDateTime dataValidade;
}