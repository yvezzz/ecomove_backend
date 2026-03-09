package com.ecomove.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité métier représentant un trajet de covoiturage.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trajet {
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
    private TrajetStatut statut;
    private LocalDateTime dateCreation;
    private UUID conducteurId;
}
