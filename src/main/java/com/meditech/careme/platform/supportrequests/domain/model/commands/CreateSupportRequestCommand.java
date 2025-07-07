package com.meditech.careme.platform.supportrequests.domain.model.commands;

import java.time.LocalDateTime;

/**
 * CreateSupportRequestCommand
 * @summary
 * Command to create a support request using primitive types and a foreign key (userId).
 */
public record CreateSupportRequestCommand(
        String type,
        String description,
        String priority,
        LocalDateTime createdAt,
        String responseStatus,
        Long userId
) {
    public CreateSupportRequestCommand {
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
