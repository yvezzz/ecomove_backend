package com.ecomove.backend.domain.ports.out;

import com.ecomove.backend.domain.model.Reservation;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepositoryPort {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(UUID id);
}
