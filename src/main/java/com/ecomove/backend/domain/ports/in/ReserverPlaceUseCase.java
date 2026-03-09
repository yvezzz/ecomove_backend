package com.ecomove.backend.domain.ports.in;

import com.ecomove.backend.domain.model.Reservation;
import java.util.UUID;

public interface ReserverPlaceUseCase {
    Reservation reserver(UUID trajetId, UUID passagerId, int nbPlaces);
}
