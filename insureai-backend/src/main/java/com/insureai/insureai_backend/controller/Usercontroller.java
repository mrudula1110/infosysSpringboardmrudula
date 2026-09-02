package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.model.User;
import com.insureai.insureai_backend.service.Userservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Usercontroller { // ← NO @RequestMapping at class level

    private final Userservice userService;

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/api/admin/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/api/admin/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    // PUBLIC - no auth needed
    @GetMapping("/api/public/agents")
    public ResponseEntity<List<User>> getPublicAgents() {
        List<User> agents = userService.getAllUsers()
                .stream()
                .filter(u -> u.getRole().equals("AGENT")
                        && u.getStatus().equals("APPROVED")
                        && u.isVerified()) // ← only verified
                .collect(Collectors.toList());
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/api/admin/agents/pending")
    public ResponseEntity<List<User>> getPendingAgents() {
        return ResponseEntity.ok(
                userService.getPendingAgents());
    }

    @PutMapping("/api/admin/agents/{id}/approve")
    public ResponseEntity<String> approveAgent(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.approveAgent(id));
    }

    @PutMapping("/api/admin/agents/{id}/reject")
    public ResponseEntity<String> rejectAgent(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                userService.rejectAgent(id));
    }
}