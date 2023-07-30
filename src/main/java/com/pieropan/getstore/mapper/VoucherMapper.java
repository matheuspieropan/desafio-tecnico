package com.pieropan.getstore.mapper;

import com.pieropan.getstore.entity.Voucher;
import com.pieropan.getstore.request.VoucherCadastroRequest;
import com.pieropan.getstore.response.VoucherCadastroResponse;
import com.pieropan.getstore.response.VoucherPorEmailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataUso", ignore = true)
    @Mapping(target = "codigoUnico", expression = "java(gerarCodigoUnico(voucherCadastroRequest))")
    Voucher toVoucher(VoucherCadastroRequest voucherCadastroRequest);

    List<VoucherPorEmailResponse> toVoucherPorEmailResponse(List<Voucher> vouchers);

    VoucherCadastroResponse toVoucherCadastroResponse(Voucher voucher);

    default String gerarCodigoUnico(VoucherCadastroRequest voucherCadastroRequest) {
        String codigo = new StringBuilder(voucherCadastroRequest.getDestinatario())
                .append(voucherCadastroRequest.getOfertaEspecial()).toString();

        int codigoByte = 0;
        for (byte b : codigo.getBytes()) {
            codigoByte = (codigoByte << 8) | (b & 0xFF);
        }
        return String.valueOf(codigoByte);
    }
}