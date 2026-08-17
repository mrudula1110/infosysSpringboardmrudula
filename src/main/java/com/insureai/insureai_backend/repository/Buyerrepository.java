package com.insureai.insureai_backend.repository;

import com.insureai.insureai_backend.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Buyerrepository extends JpaRepository<Buyer, Long> {
}