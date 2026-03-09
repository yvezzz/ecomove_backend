package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.ports.out.TrajetRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrajetPersistenceAdapter implements TrajetRepositoryPort {

    private final JpaTrajetRepository repository;

    @Override
    public Trajet save(Trajet trajet) {
        TrajetEntity entity = mapToEntity(trajet);
        TrajetEntity saved = repository.save(entity);
        repository.flush();
        return mapToDomain(saved);
    }

    @Override
    public Optional<Trajet> findById(UUID id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Trajet> findByCriteria(String depart, String arrivee, LocalDateTime date) {
        return repository.findByCriteria(depart, arrivee, date).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private TrajetEntity mapToEntity(Trajet domain) {
        TrajetEntity entity = new TrajetEntity();
        entity.setId(domain.getId());
        entity.setLatDepart(domain.getLatDepart());
        entity.setLngDepart(domain.getLngDepart());
        entity.setAdresseDepart(domain.getAdresseDepart());
        entity.setLatArrivee(domain.getLatArrivee());
        entity.setLngArrivee(domain.getLngArrivee());
        entity.setAdresseArrivee(domain.getAdresseArrivee());
        entity.setHeureDepart(domain.getHeureDepart());
        entity.setHeureArrivee(domain.getHeureArrivee());
        entity.setPlacesDispo(domain.getPlacesDispo());
        entity.setFrequence(domain.getFrequence());
        entity.setStatut(domain.getStatut());
        entity.setDateCreation(domain.getDateCreation());
        entity.setConducteurId(domain.getConducteurId());
        return entity;
    }

    private Trajet mapToDomain(TrajetEntity entity) {
        return Trajet.builder()
                .id(entity.getId())
                .latDepart(entity.getLatDepart())
                .lngDepart(entity.getLngDepart())
                .adresseDepart(entity.getAdresseDepart())
                .latArrivee(entity.getLatArrivee())
                .lngArrivee(entity.getLngArrivee())
                .adresseArrivee(entity.getAdresseArrivee())
                .heureDepart(entity.getHeureDepart())
                .heureArrivee(entity.getHeureArrivee())
                .placesDispo(entity.getPlacesDispo())
                .frequence(entity.getFrequence())
                .statut(entity.getStatut())
                .dateCreation(entity.getDateCreation())
                .conducteurId(entity.getConducteurId())
                .build();
    }
}
