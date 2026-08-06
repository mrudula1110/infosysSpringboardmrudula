package com.insureai.insureai_backend.controller;

import com.insureai.insureai_backend.dto.Authresponse;
import com.insureai.insureai_backend.dto.Loginrequest;
import com.insureai.insureai_backend.dto.Registerrequest;
import com.insureai.insureai_backend.service.Authservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class Authcontroller {

    private final Authservice authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Registerrequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Authresponse> login(@RequestBody Loginrequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}