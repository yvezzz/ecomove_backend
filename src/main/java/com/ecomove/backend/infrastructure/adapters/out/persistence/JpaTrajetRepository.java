package com.ecomove.backend.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaTrajetRepository extends JpaRepository<TrajetEntity, UUID> {
    @Query("SELECT t FROM TrajetEntity t WHERE t.adresseDepart LIKE %:depart% AND t.adresseArrivee LIKE %:arrivee% AND t.heureDepart >= :date")
    List<TrajetEntity> findByCriteria(String depart, String arrivee, LocalDateTime date);
}
