package com.ecomove.backend.domain.ports.out;

import com.ecomove.backend.domain.model.Trajet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

public interface TrajetRepositoryPort {
    Trajet save(Trajet trajet);
    Optional<Trajet> findById(UUID id);
    List<Trajet> findByCriteria(String depart, String arrivee, LocalDateTime date);
}
