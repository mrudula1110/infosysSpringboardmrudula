package com.insureai.insureai_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Authresponse {
    private String token;
    private String role;
    private String name;
}