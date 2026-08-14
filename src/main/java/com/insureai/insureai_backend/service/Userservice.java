package com.insureai.insureai_backend.service;

import com.insureai.insureai_backend.model.User;
import com.insureai.insureai_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

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
}