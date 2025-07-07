package com.meditech.careme.platform.supportrequests.domain.services;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.domain.model.commands.CreateSupportRequestCommand;

import java.util.Optional;

/**
 * @name SupportRequestCommandService
 * @summary
 * This interface represents the service to handle support request-related commands.
 */
public interface SupportRequestCommandService {
    /**
     * Handles the create support request command.
     * @param command The create support request command.
     * @return The created support request.
     * @throws IllegalArgumentException If any field is null or invalid.
     * @see CreateSupportRequestCommand
     */
    Optional<SupportRequest> handle(CreateSupportRequestCommand command);
}
