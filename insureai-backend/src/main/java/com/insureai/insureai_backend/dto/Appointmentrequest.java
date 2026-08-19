package com.insureai.insureai_backend.dto;

import lombok.Data;

@Data
public class Appointmentrequest {
    private Long customerId;
    private Long agentId;
    private String date;
    private String timeSlot;
    private String reason;
}