package com.meditech.careme.platform.caregivers.domain.services;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiverByIdQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversBySpecialtyQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversByYearsOfExperienceQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name CaregiverQueryService
 * @summary
 * This interface represents the service to handle caregiver-related queries.
 * @since 1.0.0
 */
public interface CaregiverQueryService {
    /**
     * Handles the get caregiver by ID query.
     * @param query The get caregiver by ID query.
     * @return The caregiver if exists.
     * @throws IllegalArgumentException If ID is null.
     * @see GetCaregiverByIdQuery
     */
    Optional<Caregiver> handle(GetCaregiverByIdQuery query);

    /**
     * Handles the get caregivers by specialty query.
     * @param query The get caregivers by specialty query.
     * @return List of caregivers with the specified specialty.
     * @throws IllegalArgumentException If specialty is null or empty.
     * @see GetCaregiversBySpecialtyQuery
     */
    List<Caregiver> handle(GetCaregiversBySpecialtyQuery query);

    /**
     * Handles the get caregivers by years of experience query.
     * @param query The get caregivers by years of experience query.
     * @return List of caregivers with the specified years of experience.
     * @throws IllegalArgumentException If yearsOfExperience is null or negative.
     * @see GetCaregiversByYearsOfExperienceQuery
     */
    List<Caregiver> handle(GetCaregiversByYearsOfExperienceQuery query);
}
