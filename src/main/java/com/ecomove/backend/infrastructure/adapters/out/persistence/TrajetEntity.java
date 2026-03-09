package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.TrajetStatut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trajets")
@Getter
@Setter
public class TrajetEntity {
    @Id
    private UUID id;
    
    private double latDepart;
    private double lngDepart;
    private String adresseDepart;
    
    private double latArrivee;
    private double lngArrivee;
    private String adresseArrivee;
    
    private LocalDateTime heureDepart;
    private LocalDateTime heureArrivee;
    private int placesDispo;
    private String frequence;
    
    @Enumerated(EnumType.STRING)
    private TrajetStatut statut;
    
    private LocalDateTime dateCreation;
    private UUID conducteurId;
}
