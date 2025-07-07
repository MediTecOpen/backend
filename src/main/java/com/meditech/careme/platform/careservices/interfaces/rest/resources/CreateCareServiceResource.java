package com.meditech.careme.platform.careservices.interfaces.rest.resources;

import java.sql.Time;
import java.util.UUID;

/**
 * Resource record for creating a care service.
 * @summary
 * This record represents the resource for creating a care service.
 * It includes service details and the caregiverId as a foreign key.
 * @since 1.0
 */
public record CreateCareServiceResource(
        String title,
        String patientName,
        String location,
        String tasks,
        Time startTime,
        Time endTime,
        String status,
        UUID caregiverId
) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException If any field is invalid.
     */
    public CreateCareServiceResource {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (patientName == null || patientName.isBlank())
            throw new IllegalArgumentException("Patient name cannot be null or empty");
        if (location == null || location.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");
        if (tasks == null || tasks.isBlank())
            throw new IllegalArgumentException("Tasks cannot be null or empty");
        if (startTime == null)
            throw new IllegalArgumentException("Start time cannot be null");
        if (endTime == null)
            throw new IllegalArgumentException("End time cannot be null");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status cannot be null or empty");
        if (caregiverId == null)
            throw new IllegalArgumentException("Caregiver ID cannot be null");
    }
}
