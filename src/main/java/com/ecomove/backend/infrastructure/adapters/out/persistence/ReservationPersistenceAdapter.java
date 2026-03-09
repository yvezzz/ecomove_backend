package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.Reservation;
import com.ecomove.backend.domain.ports.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReservationPersistenceAdapter implements ReservationRepositoryPort {

    private final JpaReservationRepository repository;

    @Override
    public Reservation save(Reservation domain) {
        ReservationEntity entity = mapToEntity(domain);
        ReservationEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    private ReservationEntity mapToEntity(Reservation domain) {
        ReservationEntity entity = new ReservationEntity();
        entity.setId(domain.getId());
        entity.setStatut(domain.getStatut());
        entity.setDateReservation(domain.getDateReservation());
        entity.setPlacesReservees(domain.getPlacesReservees());
        entity.setDateConfirmation(domain.getDateConfirmation());
        entity.setMotifAnnulation(domain.getMotifAnnulation());
        entity.setCo2EconomiseKg(domain.getCo2EconomiseKg());
        entity.setTrajetId(domain.getTrajetId());
        entity.setPassagerId(domain.getPassagerId());
        return entity;
    }

    private Reservation mapToDomain(ReservationEntity entity) {
        return Reservation.builder()
                .id(entity.getId())
                .statut(entity.getStatut())
                .dateReservation(entity.getDateReservation())
                .placesReservees(entity.getPlacesReservees())
                .dateConfirmation(entity.getDateConfirmation())
                .motifAnnulation(entity.getMotifAnnulation())
                .co2EconomiseKg(entity.getCo2EconomiseKg())
                .trajetId(entity.getTrajetId())
                .passagerId(entity.getPassagerId())
                .build();
    }
}
