package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.ReservationStatut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class ReservationEntity {
    @Id
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    private ReservationStatut statut;
    
    private LocalDateTime dateReservation;
    private int placesReservees;
    private LocalDateTime dateConfirmation;
    private String motifAnnulation;
    private double co2EconomiseKg;
    
    private UUID trajetId;
    private UUID passagerId;
}
