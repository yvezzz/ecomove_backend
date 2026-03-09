package com.ecomove.backend.domain.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarbonCalculationServiceTest {

    private final CarbonCalculationService carbonService = new CarbonCalculationService();

    @Test
    void shouldCalculateDistanceBetweenDoualaAndYaounde() {
        // Douala: 4.0511, 9.7679
        // Yaoundé: 3.8480, 11.5021
        double distance = carbonService.calculateDistance(4.0511, 9.7679, 3.8480, 11.5021);
        
        assertEquals(194.0, distance, 5.0); // Marge acceptable pour le calcul
    }

    @Test
    void shouldCalculateCO2Saved() {
        // Pour 100 km -> 100 * 0.19 = 19 kg CO2
        double lat1 = 48.0, lon1 = 2.0;
        double lat2 = 48.0, lon2 = 3.345; // Approx 100km sur cette latitude
        
        double co2 = carbonService.calculateCO2Saved(lat1, lon1, lat2, lon2);
        
        assertTrue(co2 > 0);
    }
}
