package com.meditech.careme.platform.caregivers.interfaces.rest.resources;

import java.util.UUID;

/**
 * Resource record for a caregiver.
 * @summary
 * This record represents the resource returned for a caregiver.
 * It includes the caregiverId and all main attributes.
 * @since 1.0
 */
public record CaregiverResource(
        UUID caregiverId,
        String name,
        int age,
        String specialty,
        int yearsOfExperience,
        String location,
        String phoneNumber,
        String professionalTitle,
        Long userId
) {
}
