package com.ecomove.backend.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaReservationRepository extends JpaRepository<ReservationEntity, UUID> {
}
