package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String nom;
    private String prenom;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String tel;
    private boolean actif;
    private LocalDateTime dateInscription;
    private String typePermis;
    private Long entrepriseId;
    
    @Enumerated(EnumType.STRING)
    private Role role;
}
