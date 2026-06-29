package com.payment.service;

import com.payment.model.Facture;
import com.payment.repository.FactureRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FactureService {

    private final FactureRepository factureRepository;

    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public List<Facture> getUnpaidInvoices(String phoneNumber) {
        return factureRepository.findByPhoneNumberAndPaidFalse(phoneNumber);
    }

    public List<Facture> getAllInvoices(String phoneNumber) {
        return factureRepository.findByPhoneNumber(phoneNumber);
    }

    public Facture markAsPaid(Long factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new IllegalArgumentException("Facture introuvable : " + factureId));
        facture.setPaid(true);
        return factureRepository.save(facture);
    }
}