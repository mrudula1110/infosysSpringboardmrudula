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

        userRepository.save(user);
        return "User registered successfully!";
    }

    public Authresponse login(Loginrequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new Authresponse(token, user.getRole(), user.getName());
    }
}