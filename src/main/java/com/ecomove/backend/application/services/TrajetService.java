package com.ecomove.backend.application.services;

import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.ports.in.RechercherTrajetUseCase;
import com.ecomove.backend.domain.ports.out.TrajetRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrajetService implements RechercherTrajetUseCase {

    private final TrajetRepositoryPort trajetRepository;

    @Override
    public List<Trajet> rechercher(String depart, String arrivee, LocalDateTime date) {
        return trajetRepository.findByCriteria(depart, arrivee, date);
    }
}
