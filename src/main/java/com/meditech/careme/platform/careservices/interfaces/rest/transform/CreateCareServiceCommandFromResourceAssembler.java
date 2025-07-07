package com.meditech.careme.platform.careservices.interfaces.rest.transform;

import com.meditech.careme.platform.careservices.domain.model.commands.CreateCareServiceCommand;
import com.meditech.careme.platform.careservices.interfaces.rest.resources.CreateCareServiceResource;

/**
 * Assembler to create a CreateCareServiceCommand from a CreateCareServiceResource.
 * @since 1.0
 */
public class CreateCareServiceCommandFromResourceAssembler {
    /**
     * Converts a CreateCareServiceResource to a CreateCareServiceCommand.
     * @param resource CreateCareServiceResource to convert
     * @return CreateCareServiceCommand created from the resource
     */
    public static CreateCareServiceCommand toCommandFromResource(CreateCareServiceResource resource) {
        return new CreateCareServiceCommand(
                resource.title(),
                resource.patientName(),
                resource.location(),
                resource.tasks(),
                resource.startTime(),
                resource.endTime(),
                resource.status(),
                resource.caregiverId()
        );
    }
}
