package com.insureai.insureai_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String message;
    private String type; // "EMAIL", "SMS", "IN_APP"
    private boolean isRead;
    private String createdAt;
}