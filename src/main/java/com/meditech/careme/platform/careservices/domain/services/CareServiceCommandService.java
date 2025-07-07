package com.meditech.careme.platform.careservices.domain.services;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.domain.model.commands.CreateCareServiceCommand;

import java.util.Optional;

/**
 * @name CareServiceCommandService
 * @summary
 * This interface represents the service to handle care service-related commands.
 */
public interface CareServiceCommandService {
    /**
     * Handles the create care service command.
     * @param command The create care service command.
     * @return The created care service.
     * @throws IllegalArgumentException If any field is null or invalid.
     * @see CreateCareServiceCommand
     */
    Optional<CareService> handle(CreateCareServiceCommand command);
}
