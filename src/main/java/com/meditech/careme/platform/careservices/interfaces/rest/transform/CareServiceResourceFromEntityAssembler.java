package com.meditech.careme.platform.careservices.interfaces.rest.transform;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.interfaces.rest.resources.CareServiceResource;

/**
 * Assembler to create a CareServiceResource from a CareService entity.
 * @since 1.0
 */
public class CareServiceResourceFromEntityAssembler {
    /**
     * Converts a CareService entity to a CareServiceResource.
     * @param entity CareService entity to convert
     * @return CareServiceResource created from the entity
     */
    public static CareServiceResource toResourceFromEntity(CareService entity) {
        return new CareServiceResource(
                entity.getCareServiceId(),
                entity.getTitle(),
                entity.getPatientName(),
                entity.getLocation(),
                entity.getTasks(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus(),
                entity.getCaregiver().getCaregiverId()
        );
    }
}
