package com.pieropan.getstore.controller;

import com.pieropan.getstore.service.voucher.VoucherService;
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

    @GetMapping
    public ResponseEntity getVoucher() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{codigoVoucher}/{email}")
    public ResponseEntity<Object> utilizarVoucher(@PathVariable String codigoVoucher, @PathVariable String email) {
        return ResponseEntity.ok(service.utilizarVoucher(codigoVoucher, email));
    }
}