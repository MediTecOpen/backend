package com.meditech.careme.platform.careservices.domain.model.queries;

import java.util.UUID;

/**
 * @summary
 * This class represents the query to get a care service by its UUID.
 * @param careServiceId - the UUID of the care service.
 */
public record GetCareServiceByIdQuery(UUID careServiceId) {
    public GetCareServiceByIdQuery {
        if (careServiceId == null) {
            throw new IllegalArgumentException("CareServiceId cannot be null");
        }
    }
}
