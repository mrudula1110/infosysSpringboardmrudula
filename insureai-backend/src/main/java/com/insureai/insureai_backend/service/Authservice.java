package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.config.JwtUtil;
import com.insureai.insureai_backend.dto.Authresponse;
import com.insureai.insureai_backend.dto.Loginrequest;
import com.insureai.insureai_backend.dto.Registerrequest;
import com.insureai.insureai_backend.model.User;
import com.insureai.insureai_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.insureai.insureai_backend.dto.Authresponse;
import com.insureai.insureai_backend.dto.Loginrequest;
import com.insureai.insureai_backend.dto.Registerrequest;
import com.insureai.insureai_backend.model.User;
import com.insureai.insureai_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Authservice {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(Registerrequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());
        user.setVerified(false);
        if (request.getRole().equals("AGENT")) {
            // Agent needs admin approval
            user.setVerified(false);
            user.setStatus("PENDING");
        } else {
            // Customer and Admin auto approved
            user.setVerified(true);
            user.setStatus("APPROVED");
        }

        userRepository.save(user);
        if (request.getRole().equals("AGENT")) {
            return "Registration successful! " +
                    "Please wait for admin approval " +
                    "before you can start receiving appointments.";
        }
        return "Registration successful! You can now login.";
    }

    public Authresponse login(Loginrequest request) {
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        // Only check status for AGENT role
        if ("AGENT".equals(user.getRole())) {
            String status = user.getStatus();

            if (status == null ||
                    "PENDING".equals(status)) {
                throw new RuntimeException(
                        "PENDING: Your account is awaiting " +
                                "admin approval!");
            }

            if ("REJECTED".equals(status)) {
                throw new RuntimeException(
                        "REJECTED: Your account was rejected. " +
                                "Contact support!");
            }
        }

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole());

        return new Authresponse(
                token, user.getRole(), user.getName());
    }
}