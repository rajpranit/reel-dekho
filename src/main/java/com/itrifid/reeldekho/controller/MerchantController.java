package com.itrifid.reeldekho.controller;

import com.itrifid.reeldekho.entity.Merchant;
import com.itrifid.reeldekho.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        return ResponseEntity.ok(merchantService.createMerchant(merchant));
    }

    @GetMapping
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Merchant>> getMerchantById(@PathVariable UUID id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable UUID id, @RequestBody Merchant updatedMerchant) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, updatedMerchant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.ok("Merchant deleted successfully");
    }

    @PostMapping("/{id}/promote")
    public ResponseEntity<String> promoteMerchant(@PathVariable UUID id, @RequestBody String promotionDetails) {
        merchantService.promoteMerchant(id, promotionDetails);
        return ResponseEntity.ok("Merchant promotion enabled successfully");
    }
}
