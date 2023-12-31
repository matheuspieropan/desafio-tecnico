package com.pieropan.getstore.repository;

import com.pieropan.getstore.entity.Voucher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends CrudRepository<Voucher, Long> {

    Optional<Voucher> findByCodigoVoucherAndEmail(String codigoVoucher, String email);

    List<Voucher> findByEmail(String email);

    Optional<Voucher> findByDestinatario(String destinatario);
}