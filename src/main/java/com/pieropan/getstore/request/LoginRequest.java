package com.pieropan.getstore.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

    @Schema(defaultValue = "admin@admin.com")
    private String email;

    @Schema(defaultValue = "123")
    private String senha;
}