package com.ecomove.backend.application.services;

import com.ecomove.backend.domain.model.Reservation;
import com.ecomove.backend.domain.model.ReservationStatut;
import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.ports.in.ReserverPlaceUseCase;
import com.ecomove.backend.domain.ports.out.ReservationRepositoryPort;
import com.ecomove.backend.domain.ports.out.TrajetRepositoryPort;
import com.ecomove.backend.domain.service.CarbonCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReserverPlaceUseCase {

    private final ReservationRepositoryPort reservationRepository;
    private final TrajetRepositoryPort trajetRepository;
    private final CarbonCalculationService carbonCalculationService;

    @Override
    @Transactional
    public Reservation reserver(UUID trajetId, UUID passagerId, int nbPlaces) {
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));

        if (trajet.getPlacesDispo() < nbPlaces) {
            throw new RuntimeException("Nombre de places insuffisant");
        }

        // Calcul du CO2 économisé
        double co2 = carbonCalculationService.calculateCO2Saved(
                trajet.getLatDepart(), trajet.getLngDepart(),
                trajet.getLatArrivee(), trajet.getLngArrivee()
        );

        Reservation reservation = Reservation.builder()
                .id(UUID.randomUUID())
                .trajetId(trajetId)
                .passagerId(passagerId)
                .placesReservees(nbPlaces)
                .statut(ReservationStatut.PENDING)
                .dateReservation(LocalDateTime.now())
                .co2EconomiseKg(co2)
                .build();

        // Mise à jour des places disponibles
        trajet.setPlacesDispo(trajet.getPlacesDispo() - nbPlaces);
        trajetRepository.save(trajet);

        return reservationRepository.save(reservation);
    }
}
