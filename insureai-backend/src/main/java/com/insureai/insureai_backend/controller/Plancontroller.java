package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.model.Plan;
import com.insureai.insureai_backend.service.Planservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/plans")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Plancontroller {

    private final Planservice planservice;

    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans() {
        return ResponseEntity.ok(planservice.getAllPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(planservice.getPlanById(id));
    }

    @PostMapping
    public ResponseEntity<Plan> addPlan(@RequestBody Plan plan) {
        return ResponseEntity.ok(planservice.addPlan(plan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id,
            @RequestBody Plan plan) {
        return ResponseEntity.ok(planservice.updatePlan(id, plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        return ResponseEntity.ok(planservice.deletePlan(id));
    }

    // Public endpoint - no auth needed
    @GetMapping("/public/plans")
    public ResponseEntity<List<Plan>> getPublicPlans() {
        return ResponseEntity.ok(planservice.getAllPlans());
    }
}