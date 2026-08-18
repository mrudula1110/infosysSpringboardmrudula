package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.dto.Availabilityrequest;
import com.insureai.insureai_backend.model.Agentavailability;
import com.insureai.insureai_backend.service.AgentAvailabilityservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Agentavailabilitycontroller {

    private final AgentAvailabilityservice availabilityService;

    // Agent sets their availability
    @PostMapping("/api/agent/availability")
    public ResponseEntity<Agentavailability> setAvailability(
            @RequestBody Availabilityrequest request) {
        return ResponseEntity.ok(
                availabilityService.setAvailability(request));
    }

    // Get all slots for an agent
    @GetMapping("/api/agent/availability/{agentId}")
    public ResponseEntity<List<Agentavailability>> getAgentAvailability(
            @PathVariable Long agentId) {
        return ResponseEntity.ok(
                availabilityService.getAgentAvailability(agentId));
    }

    // Get slots by date
    @GetMapping("/api/agent/availability/{agentId}/{date}")
    public ResponseEntity<List<Agentavailability>> getByDate(
            @PathVariable Long agentId,
            @PathVariable String date) {
        return ResponseEntity.ok(
                availabilityService.getAvailabilityByDate(agentId, date));
    }

    // Customer views available slots of an agent
    @GetMapping("/api/customer/availability/{agentId}")
    public ResponseEntity<List<Agentavailability>> getAvailableSlots(
            @PathVariable Long agentId) {
        return ResponseEntity.ok(
                availabilityService.getAvailableSlots(agentId));
    }

    // Agent updates a slot
    @PutMapping("/api/agent/availability/{id}")
    public ResponseEntity<Agentavailability> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean isAvailable) {
        return ResponseEntity.ok(
                availabilityService.updateAvailability(id, isAvailable));
    }

    // Agent deletes a slot
    @DeleteMapping("/api/agent/availability/{id}")
    public ResponseEntity<String> deleteAvailability(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                availabilityService.deleteAvailability(id));
    }
}