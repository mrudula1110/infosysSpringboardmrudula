package com.insureai.insureai_backend.dto;

import lombok.Data;

@Data
public class Availabilityrequest {
    private Long agentId;
    private String date;
    private String timeSlot;
    private boolean isAvailable;
}