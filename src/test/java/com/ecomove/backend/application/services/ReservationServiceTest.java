package com.ecomove.backend.application.services;

import com.ecomove.backend.domain.model.Reservation;
import com.ecomove.backend.domain.model.Trajet;
import com.ecomove.backend.domain.ports.out.ReservationRepositoryPort;
import com.ecomove.backend.domain.ports.out.TrajetRepositoryPort;
import com.ecomove.backend.domain.service.CarbonCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepositoryPort reservationRepository;
    @Mock
    private TrajetRepositoryPort trajetRepository;
    @Mock
    private CarbonCalculationService carbonCalculationService;

    @InjectMocks
    private ReservationService reservationService;

    private UUID trajetId;
    private UUID passagerId;
    private Trajet trajet;

    @BeforeEach
    void setUp() {
        trajetId = UUID.randomUUID();
        passagerId = UUID.randomUUID();
        trajet = Trajet.builder()
                .id(trajetId)
                .placesDispo(3)
                .latDepart(48.0)
                .lngDepart(2.0)
                .latArrivee(49.0)
                .lngArrivee(3.0)
                .build();
    }

    @Test
    void shouldReserverSuccessfully() {
        // Given
        when(trajetRepository.findById(trajetId)).thenReturn(Optional.of(trajet));
        when(carbonCalculationService.calculateCO2Saved(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(5.5);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        Reservation result = reservationService.reserver(trajetId, passagerId, 2);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getPlacesReservees());
        assertEquals(5.5, result.getCo2EconomiseKg());
        assertEquals(1, trajet.getPlacesDispo()); // 3 - 2 = 1
        
        verify(trajetRepository).save(trajet);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughPlaces() {
        // Given
        when(trajetRepository.findById(trajetId)).thenReturn(Optional.of(trajet));

        // When & Then
        assertThrows(RuntimeException.class, () -> 
            reservationService.reserver(trajetId, passagerId, 5)
        );
        
        verify(reservationRepository, never()).save(any());
    }
}
