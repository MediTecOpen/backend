package com.meditech.careme.platform.supportrequests.domain.services;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.domain.model.queries.GetSupportRequestByIdQuery;

import java.util.Optional;

/**
 * @name SupportRequestQueryService
 * @summary
 * This interface represents the service to handle support request-related queries.
 * @since 1.0
 */
public interface SupportRequestQueryService {
    /**
     * Handles the get support request by ID query.
     * @param query The get support request by ID query.
     * @return The support request if it exists.
     * @throws IllegalArgumentException If ID is null.
     * @see GetSupportRequestByIdQuery
     */
    Optional<SupportRequest> handle(GetSupportRequestByIdQuery query);
}
