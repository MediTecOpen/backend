package com.meditech.careme.platform.supportrequests.domain.model.queries;

import java.util.UUID;

/**
 * @summary
 * This class represents the query to get a support request by its UUID.
 * @param supportRequestId - the UUID of the support request.
 */
public record GetSupportRequestByIdQuery(UUID supportRequestId) {
    public GetSupportRequestByIdQuery {
        if (supportRequestId == null) {
            throw new IllegalArgumentException("SupportRequestId cannot be null");
        }
    }
}
