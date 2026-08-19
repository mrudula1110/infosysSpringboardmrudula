package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.model.Buyer;
import com.insureai.insureai_backend.repository.Buyerrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Buyerservice {

    private final Buyerrepository buyerRepository;

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found!"));
    }

    public Buyer addBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyer(Long id, Buyer updated) {
        Buyer buyer = getBuyerById(id);
        buyer.setName(updated.getName());
        buyer.setMobile(updated.getMobile());
        buyer.setEmail(updated.getEmail());
        buyer.setAddress(updated.getAddress());
        return buyerRepository.save(buyer);
    }

    public String deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
        return "Buyer deleted successfully!";
    }
}