package com.meditech.careme.platform.supportrequests.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Resource record for creating a support request.
 * @summary
 * This record represents the resource for creating a support request.
 * It includes all support request fields and the userId as a foreign key.
 * @since 1.0
 */
public record CreateSupportRequestResource(
        String type,
        String description,
        String priority,
        LocalDateTime createdAt,
        String responseStatus,
        Long userId
) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException If any field is invalid.
     */
    public CreateSupportRequestResource {
        if (type == null || type.isBlank())
            throw new IllegalArgumentException("Type cannot be null or empty");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty");
        if (priority == null || priority.isBlank())
            throw new IllegalArgumentException("Priority cannot be null or empty");
        if (createdAt == null)
            throw new IllegalArgumentException("CreatedAt cannot be null");
        if (responseStatus == null || responseStatus.isBlank())
            throw new IllegalArgumentException("Response status cannot be null or empty");
        if (userId == null)
            throw new IllegalArgumentException("User ID cannot be null");
    }
}
