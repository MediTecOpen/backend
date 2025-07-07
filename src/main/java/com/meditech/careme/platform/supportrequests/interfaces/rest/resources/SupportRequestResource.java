package com.meditech.careme.platform.supportrequests.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Resource record for a support request.
 * @summary
 * This record represents the resource returned for a support request.
 * It includes the supportRequestId and all relevant attributes.
 * @since 1.0
 */
public record SupportRequestResource(
        UUID supportRequestId,
        String type,
        String description,
        String priority,
        LocalDateTime createdAt,
        String responseStatus,
        Long userId
) {
}
