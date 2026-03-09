package com.ecomove.backend.infrastructure.adapters.in.web;

import com.ecomove.backend.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private Role role;
    private Long entrepriseId;
}
