package com.payment.controller;

import com.payment.model.Facture;
import com.payment.service.FactureService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping
    public ResponseEntity<List<Facture>> getUnpaidInvoices(@RequestParam String phone) {
        return ResponseEntity.ok(factureService.getUnpaidInvoices(phone));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Facture>> getAllInvoices(@RequestParam String phone) {
        return ResponseEntity.ok(factureService.getAllInvoices(phone));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Facture> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(factureService.markAsPaid(id));
    }
}