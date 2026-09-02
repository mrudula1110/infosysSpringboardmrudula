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

    // Approve agent
    public String approveAgent(Long id) {
        User user = getUserById(id);
        if (!"AGENT".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("User is not an agent! Role found: " + user.getRole());
        }
        user.setStatus("APPROVED");
        user.setVerified(true);
        userRepository.save(user);
        return user.getName() + " has been approved!";
    }

    // Reject agent - DELETE from database
    public String rejectAgent(Long id) {
        User user = getUserById(id);
        if (!"AGENT".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("User is not an agent! Role found: " + user.getRole());
        }
        String name = user.getName();
        userRepository.deleteById(id); // ← DELETE completely
        return name + " has been rejected and removed!";
    }

    public List<User> getPendingAgents() {
        return userRepository.findAll()
                .stream()
                .filter(u -> "AGENT".equalsIgnoreCase(u.getRole())
                        && "PENDING".equalsIgnoreCase(u.getStatus()))
                .collect(Collectors.toList());
    }
}