package com.meditech.careme.platform.careservices.domain.model.commands;

import java.sql.Time;
import java.util.UUID;

/**
 * CreateCareServiceCommand
 * @summary
 * Command to create a care service using primitive types and a foreign key (caregiverId).
 */
public record CreateCareServiceCommand(
        String title,
        String patientName,
        String location,
        String tasks,
        Time startTime,
        Time endTime,
        String status,
        UUID caregiverId
) {
    public CreateCareServiceCommand {
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
