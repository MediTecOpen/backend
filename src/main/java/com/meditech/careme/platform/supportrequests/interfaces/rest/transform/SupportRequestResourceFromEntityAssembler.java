package com.meditech.careme.platform.supportrequests.interfaces.rest.transform;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.interfaces.rest.resources.SupportRequestResource;

/**
 * Assembler to create a SupportRequestResource from a SupportRequest entity.
 * @since 1.0
 */
public class SupportRequestResourceFromEntityAssembler {
    /**
     * Converts a SupportRequest entity to a SupportRequestResource.
     * @param entity SupportRequest entity to convert
     * @return SupportRequestResource created from the entity
     */
    public static SupportRequestResource toResourceFromEntity(SupportRequest entity) {
        return new SupportRequestResource(
                entity.getSupportRequestId(),
                entity.getType(),
                entity.getDescription(),
                entity.getPriority(),
                entity.getCreatedAt(),
                entity.getResponseStatus(),
                entity.getUser().getUserId()
        );
    }
}
