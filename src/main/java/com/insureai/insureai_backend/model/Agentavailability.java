package com.insureai.insureai_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agent_availability")
public class Agentavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agentId;
    private String date;
    private String timeSlot;
    private boolean isAvailable;
}