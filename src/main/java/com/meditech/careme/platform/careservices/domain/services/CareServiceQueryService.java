package com.meditech.careme.platform.careservices.domain.services;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.domain.model.queries.GetCareServiceByIdQuery;

import java.util.Optional;

/**
 * @name CareServiceQueryService
 * @summary
 * This interface represents the service to handle care service-related queries.
 * @since 1.0.0
 */
public interface CareServiceQueryService {
    /**
     * Handles the get care service by ID query.
     * @param query The get care service by ID query.
     * @return The care service if it exists.
     * @throws IllegalArgumentException If ID is null.
     * @see GetCareServiceByIdQuery
     */
    Optional<CareService> handle(GetCareServiceByIdQuery query);
}
