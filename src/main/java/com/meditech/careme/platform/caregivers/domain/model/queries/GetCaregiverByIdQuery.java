package com.meditech.careme.platform.caregivers.domain.model.queries;

import java.util.UUID;

/**
 * @summary
 * This class represents the query to get a caregiver by its UUID.
 * @param caregiverId - the UUID of the caregiver.
 */
public record GetCaregiverByIdQuery(UUID caregiverId) {
    public GetCaregiverByIdQuery {
        if (caregiverId == null) {
            throw new IllegalArgumentException("CaregiverId cannot be null");
        }
    }
}