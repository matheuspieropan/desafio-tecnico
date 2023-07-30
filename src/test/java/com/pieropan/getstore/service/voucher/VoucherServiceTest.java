package com.pieropan.getstore.service.voucher;

import com.pieropan.getstore.exception.ExcecaoCustomizada;
import com.pieropan.getstore.repository.VoucherRepository;
import com.pieropan.getstore.request.VoucherCadastroRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VoucherServiceTest {

    @Autowired
    VoucherService voucherService;

    @Autowired
    VoucherRepository voucherRepository;

    @BeforeEach
    public void criarVoucher() {
        voucherService.criarVoucher(new VoucherCadastroRequest(
                "Matheus",
                "Voucher tal tal...",
                "matheus@gmail.com",
                "Oferta iPhone 14",
                10,
                "ABC123",
                LocalDateTime.now()));
    }

    @AfterEach
    public void removeVoucher() {
        voucherRepository.deleteAll();
    }

    @Test
    public void testUtilizarVoucherComSucesso() {
        int percentualDesconto = voucherService.utilizarVoucher("ABC123", "matheus@gmail.com");
        assertEquals(10, percentualDesconto);
    }

    @Test
    public void testUtilizarVoucherComErro() {
        voucherService.utilizarVoucher("ABC123", "matheus@gmail.com");
        assertThrows(ExcecaoCustomizada.class, () -> {
            voucherService.utilizarVoucher("ABC123", "matheus@gmail.com");
        });
    }
}