package com.pieropan.getstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @GetMapping
    public ResponseEntity getVoucher() {
        return ResponseEntity.ok().build();
    }
}