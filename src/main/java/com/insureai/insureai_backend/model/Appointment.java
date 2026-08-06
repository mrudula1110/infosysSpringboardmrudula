package com.insureai.insureai_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long agentId;
    private String date;
    private String timeSlot;
    private String reason;
    private String status; // "PENDING", "CONFIRMED", "CANCELLED"
}