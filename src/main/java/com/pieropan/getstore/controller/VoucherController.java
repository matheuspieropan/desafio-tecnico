package com.pieropan.getstore.controller;

import com.pieropan.getstore.service.voucher.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/voucher")
public class VoucherController {

    VoucherService service;

    @Operation(summary = "Utiliza um voucher recebendo o código do voucher e e-mail como argumentos.")
    @GetMapping("/{codigoVoucher}/{email}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de negócio")
    })
    public ResponseEntity<Object> utilizarVoucher(@PathVariable String codigoVoucher, @PathVariable String email) {
        return ResponseEntity.ok(service.utilizarVoucher(codigoVoucher, email));
    }
}