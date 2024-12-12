package com.itrifid.reeldekho.service;

import com.itrifid.reeldekho.entity.Merchant;
import com.itrifid.reeldekho.repository.MerchantRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {

    private final com.itrifid.reeldekho.repository.MerchantRepo MerchantRepo;

    public MerchantService(MerchantRepo MerchantRepo) {
        this.MerchantRepo = MerchantRepo;
    }

    public Merchant createMerchant(Merchant merchant) {
        return MerchantRepo.save(merchant);
    }

    public List<Merchant> getAllMerchants() {
        return MerchantRepo.findAll();
    }

    public Optional<Merchant> getMerchantById(UUID id) {
        return MerchantRepo.findById(id);
    }

    public Merchant updateMerchant(UUID id, Merchant updatedMerchant) {
        return MerchantRepo.findById(id).map(merchant -> {
            merchant.setName(updatedMerchant.getName());
            merchant.setEmail(updatedMerchant.getEmail());
            merchant.setPhone(updatedMerchant.getPhone());
            merchant.setAddress(updatedMerchant.getAddress());
            merchant.setCategory(updatedMerchant.getCategory());
            merchant.setLogo(updatedMerchant.getLogo());
            return MerchantRepo.save(merchant);
        }).orElseThrow(() -> new RuntimeException("Merchant not found"));
    }

    public void deleteMerchant(UUID id) {
        MerchantRepo.deleteById(id);
    }

    public Merchant promoteMerchant(UUID id, String promotionDetails) {
        return MerchantRepo.findById(id).map(merchant -> {
            merchant.setIsPromoted(true);
            // Add promotion details if necessary
            return MerchantRepo.save(merchant);
        }).orElseThrow(() -> new RuntimeException("Merchant not found"));
    }
}