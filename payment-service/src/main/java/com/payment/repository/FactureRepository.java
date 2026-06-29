package com.payment.repository;

import com.payment.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findByPhoneNumberAndPaidFalse(String phoneNumber);

    List<Facture> findByPhoneNumber(String phoneNumber);
}