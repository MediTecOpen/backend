package com.meditech.careme.platform.supportrequests.interfaces.rest.transform;

import com.meditech.careme.platform.supportrequests.domain.model.commands.CreateSupportRequestCommand;
import com.meditech.careme.platform.supportrequests.interfaces.rest.resources.CreateSupportRequestResource;

/**
 * Assembler to create a CreateSupportRequestCommand from a CreateSupportRequestResource.
 * @since 1.0
 */
public class CreateSupportRequestCommandFromResourceAssembler {
    /**
     * Converts a CreateSupportRequestResource to a CreateSupportRequestCommand.
     * @param resource CreateSupportRequestResource to convert
     * @return CreateSupportRequestCommand created from the resource
     */
    public static CreateSupportRequestCommand toCommandFromResource(CreateSupportRequestResource resource) {
        return new CreateSupportRequestCommand(
                resource.type(),
                resource.description(),
                resource.priority(),
                resource.createdAt(),
                resource.responseStatus(),
                resource.userId()
        );
    }
}
