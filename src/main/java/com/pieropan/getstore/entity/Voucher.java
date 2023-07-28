package com.pieropan.getstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "voucher")
public class Voucher {

    @Id
    private String id;

    private String destinatario;

    private String nome;

    private String email;

    private String ofertaEspecial;

    private int descontoPercentualFixo;

    private String codigoVoucher;

    private String codigoUnico;

    private LocalDateTime dataValidade;

    private LocalDateTime dataUso;
}