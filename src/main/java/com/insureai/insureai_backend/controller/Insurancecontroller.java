package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.model.Insurance;
import com.insureai.insureai_backend.service.Insuranceservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/insurance")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Insurancecontroller {

    private final Insuranceservice insuranceService;

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurance() {
        return ResponseEntity.ok(insuranceService.getAllInsurance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.getInsuranceById(id));
    }

    @PostMapping
    public ResponseEntity<Insurance> addInsurance(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.addInsurance(insurance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable Long id,
            @RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.updateInsurance(id, insurance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.deleteInsurance(id));
    }
}