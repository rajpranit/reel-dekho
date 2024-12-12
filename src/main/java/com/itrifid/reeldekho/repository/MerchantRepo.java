package com.itrifid.reeldekho.repository;

import com.itrifid.reeldekho.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepo extends JpaRepository<Merchant, UUID> {
}