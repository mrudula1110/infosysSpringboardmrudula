package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.model.User;
import com.insureai.insureai_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Userservice {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }

    public String approveAgent(Long id) {
        User user = getUserById(id);
        if (!user.getRole().equals("AGENT")) {
            throw new RuntimeException("User is not an agent!");
        }
        user.setStatus("APPROVED");
        user.setVerified(true); // ← mark as verified
        userRepository.save(user);
        return "Agent approved successfully!";
    }

    public String rejectAgent(Long id) {
        User user = getUserById(id);
        if (!user.getRole().equals("AGENT")) {
            throw new RuntimeException("User is not an agent!");
        }
        user.setStatus("REJECTED");
        user.setVerified(false); // ← stays unverified
        userRepository.save(user);
        return "Agent rejected!";
    }

    public List<User> getPendingAgents() {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getRole().equals("AGENT")
                        && "PENDING".equals(u.getStatus()))
                .collect(Collectors.toList());
    }
}