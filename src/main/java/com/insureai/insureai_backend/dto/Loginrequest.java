package com.insureai.insureai_backend.dto;

import lombok.Data;

@Data
public class Loginrequest {
    private String email;
    private String password;
}