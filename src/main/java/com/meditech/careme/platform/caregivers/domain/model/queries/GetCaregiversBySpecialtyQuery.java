package com.meditech.careme.platform.caregivers.domain.model.queries;

/**
 * @summary
 * This class represents the query to get caregivers by their specialty.
 * @param specialty - the specialty of the caregivers.
 */
public record GetCaregiversBySpecialtyQuery(String specialty) {
    public GetCaregiversBySpecialtyQuery {
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be null or empty");
        }
    }
}