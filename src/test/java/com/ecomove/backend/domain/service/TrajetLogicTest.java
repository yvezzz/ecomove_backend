package com.ecomove.backend.domain.service;

import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.model.TrajetStatut;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrajetLogicTest {

    @Test
    void shouldCreateTrajetWithInitialState() {
        Trajet trajet = Trajet.builder()
                .id(UUID.randomUUID())
                .adresseDepart("Douala (Akwa)")
                .adresseArrivee("Yaoundé (Mvan)")
                .placesDispo(3)
                .statut(TrajetStatut.PUBLIE)
                .dateCreation(LocalDateTime.now())
                .build();
        
        assertNotNull(trajet.getId());
        assertEquals("Douala (Akwa)", trajet.getAdresseDepart());
        assertEquals(3, trajet.getPlacesDispo());
        assertEquals(TrajetStatut.PUBLIE, trajet.getStatut());
    }
}
