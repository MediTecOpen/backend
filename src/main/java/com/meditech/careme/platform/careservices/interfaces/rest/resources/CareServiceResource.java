package com.meditech.careme.platform.careservices.interfaces.rest.resources;

import java.sql.Time;
import java.util.UUID;

/**
 * Resource record for a care service.
 * @summary
 * This record represents the resource returned for a care service.
 * It includes the careServiceId and all relevant attributes.
 * @since 1.0
 */
public record CareServiceResource(
        UUID careServiceId,
        String title,
        String patientName,
        String location,
        String tasks,
        Time startTime,
        Time endTime,
        String status,
        UUID caregiverId
) {
}
