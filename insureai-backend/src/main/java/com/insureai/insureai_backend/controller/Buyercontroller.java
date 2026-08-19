package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.model.Buyer;
import com.insureai.insureai_backend.service.Buyerservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/buyers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Buyercontroller {

    private final Buyerservice buyerService;

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        return ResponseEntity.ok(buyerService.getAllBuyers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        return ResponseEntity.ok(buyerService.getBuyerById(id));
    }

    @PostMapping
    public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer) {
        return ResponseEntity.ok(buyerService.addBuyer(buyer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id,
            @RequestBody Buyer buyer) {
        return ResponseEntity.ok(buyerService.updateBuyer(id, buyer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuyer(@PathVariable Long id) {
        return ResponseEntity.ok(buyerService.deleteBuyer(id));
    }
}