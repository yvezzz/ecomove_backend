package com.ecomove.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité métier représentant un utilisateur (conducteur, passager ou employeur).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String password;
    private boolean actif;
    private LocalDateTime dateInscription;
    private String typePermis;
    private Long entrepriseId;
    private Role role;
}
