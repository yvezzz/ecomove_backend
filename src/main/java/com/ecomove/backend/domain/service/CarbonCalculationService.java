package com.ecomove.backend.domain.service;

import org.springframework.stereotype.Service;

/**
 * Service pour le calcul de l'impact carbone.
 * Basé sur la loi LOM et les facteurs d'émission moyens.
 */
@Service
public class CarbonCalculationService {

    // Facteur d'émission moyen adapté au parc automobile local (kg CO2 / km)
    // On augmente légèrement le facteur (0.22) pour refléter l'âge moyen des véhicules
    private static final double EMISSION_FACTOR_KG_PER_KM = 0.22;
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calcule le CO2 économisé pour un trajet donné.
     * @param lat1 Latitude départ
     * @param lon1 Longitude départ
     * @param lat2 Latitude arrivée
     * @param lon2 Longitude arrivée
     * @return kg de CO2 économisés
     */
    public double calculateCO2Saved(double lat1, double lon1, double lat2, double lon2) {
        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        return distance * EMISSION_FACTOR_KG_PER_KM;
    }

    /**
     * Calcule la distance entre deux points GPS en utilisant la formule de Haversine.
     */
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
