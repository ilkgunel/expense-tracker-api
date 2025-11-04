package com.ilkaygunel.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private final String userName;
    private final String password;
}
