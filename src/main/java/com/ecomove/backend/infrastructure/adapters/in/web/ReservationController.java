package com.ecomove.backend.infrastructure.adapters.in.web;

import com.ecomove.backend.domain.model.Reservation;
import com.ecomove.backend.domain.ports.in.ReserverPlaceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservations", description = "Gestion des réservations de covoiturage")
public class ReservationController {

    private final ReserverPlaceUseCase reserverPlaceUseCase;

    @PostMapping
    @Operation(summary = "Créer une nouvelle réservation")
    public ResponseEntity<Reservation> create(@RequestBody ReservationRequest request) {
        Reservation reservation = reserverPlaceUseCase.reserver(
                request.getTrajetId(),
                request.getPassagerId(),
                request.getNbPlaces()
        );
        return ResponseEntity.ok(reservation);
    }

    @Data
    public static class ReservationRequest {
        private UUID trajetId;
        private UUID passagerId;
        private int nbPlaces;
    }
}
