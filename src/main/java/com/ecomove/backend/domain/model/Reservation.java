package com.ecomove.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité métier représentant une réservation sur un trajet.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private UUID id;
    private ReservationStatut statut;
    private LocalDateTime dateReservation;
    private int placesReservees;
    private LocalDateTime dateConfirmation;
    private String motifAnnulation;
    private double co2EconomiseKg;
    private UUID trajetId;
    private UUID passagerId;
}
