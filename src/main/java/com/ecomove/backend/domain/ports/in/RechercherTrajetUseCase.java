package com.ecomove.backend.domain.ports.in;

import com.ecomove.backend.domain.model.Trajet;
import java.time.LocalDateTime;
import java.util.List;

public interface RechercherTrajetUseCase {
    List<Trajet> rechercher(String depart, String arrivee, LocalDateTime date);
}
