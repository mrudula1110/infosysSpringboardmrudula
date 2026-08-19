package com.insureai.insureai_backend.dto;

import lombok.Data;

@Data
public class Registerrequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String dob;
}