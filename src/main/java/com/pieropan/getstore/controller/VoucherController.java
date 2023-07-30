package com.pieropan.getstore.controller;

import com.pieropan.getstore.request.VoucherCadastroRequest;
import com.pieropan.getstore.response.VoucherPorEmailResponse;
import com.pieropan.getstore.service.voucher.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping("/voucher")
public class VoucherController {

    VoucherService service;

    @PostMapping
    @Operation(summary = "Cria um voucher recebendo um json no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso. Retorna o voucher criado"),
            @ApiResponse(responseCode = "400", description = "Erro de negócio")
    })
    public ResponseEntity criarVoucher(@RequestBody VoucherCadastroRequest voucherCadastroRequest) {
        return ResponseEntity.status(CREATED.value()).body(service.criarVoucher(voucherCadastroRequest));
    }

    @GetMapping("/{codigoVoucher}/{email}")
    @Operation(summary = "Utiliza um voucher recebendo o código do voucher e e-mail como argumentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso. Retorna o percentual de desconto"),
            @ApiResponse(responseCode = "400", description = "Erro de negócio")
    })
    public ResponseEntity<Integer> utilizarVoucher(@PathVariable String codigoVoucher, @PathVariable String email) {
        return ResponseEntity.ok(service.utilizarVoucher(codigoVoucher, email));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Obtém uma lista de vouchers com suas ofertas recebendo um e-mail como argumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso. Retorna uma lista de códigos de vouchers e ofertas especiais"),
            @ApiResponse(responseCode = "400", description = "Erro de negócio. Não foi encontrado vouchers para este e-mail")
    })
    public ResponseEntity<List<VoucherPorEmailResponse>> obterVoucherPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.obterVoucherPorEmail(email));
    }
}