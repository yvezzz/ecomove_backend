package com.ecomove.backend.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AttestationLOM {
    private UUID userId;
    private String nomComplet;
    private String entreprise;
    private double totalCo2Economise;
    private int nombreTrajets;
    private LocalDateTime dateGeneration;
    private String periode;
}
