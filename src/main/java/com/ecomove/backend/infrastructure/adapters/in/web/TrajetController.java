package com.ecomove.backend.infrastructure.adapters.in.web;

import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.ports.in.RechercherTrajetUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trajets")
@RequiredArgsConstructor
@Tag(name = "Trajets", description = "Recherche et consultation des trajets")
public class TrajetController {

    private final RechercherTrajetUseCase rechercherTrajetUseCase;

    @GetMapping("/search")
    @Operation(summary = "Rechercher des trajets disponibles")
    public ResponseEntity<List<Trajet>> search(
            @RequestParam String depart,
            @RequestParam String arrivee,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(rechercherTrajetUseCase.rechercher(depart, arrivee, date));
    }
}
