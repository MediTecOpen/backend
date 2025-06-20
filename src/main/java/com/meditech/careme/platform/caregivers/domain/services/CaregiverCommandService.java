package com.meditech.careme.platform.caregivers.domain.services;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.domain.model.commands.CreateCaregiverCommand;

import java.util.Optional;

/**
 * @name CaregiverCommandService
 * @summary
 * This interface represents the service to handle caregiver-related commands.
 */
public interface CaregiverCommandService {
    /**
     * Handles the create caregiver command.
     * @param command The create caregiver command.
     * @return The created caregiver.
     * @throws IllegalArgumentException If any caregiver field is null or invalid.
     * @see CreateCaregiverCommand
     */
    Optional<Caregiver> handle(CreateCaregiverCommand command);
}
