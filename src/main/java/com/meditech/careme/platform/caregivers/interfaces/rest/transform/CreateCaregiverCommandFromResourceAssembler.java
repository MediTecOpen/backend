package com.meditech.careme.platform.caregivers.interfaces.rest.transform;

import com.meditech.careme.platform.caregivers.domain.model.commands.CreateCaregiverCommand;
import com.meditech.careme.platform.caregivers.interfaces.rest.resources.CreateCaregiverResource;

/**
 * Assembler to create a CreateCaregiverCommand from a CreateCaregiverResource.
 * @since 1.0
 */
public class CreateCaregiverCommandFromResourceAssembler {
    /**
     * Converts a CreateCaregiverResource to a CreateCaregiverCommand.
     * @param resource CreateCaregiverResource to convert
     * @return CreateCaregiverCommand created from the resource
     */
    public static CreateCaregiverCommand toCommandFromResource(CreateCaregiverResource resource) {
        return new CreateCaregiverCommand(
                resource.name(),
                resource.age(),
                resource.specialty(),
                resource.yearsOfExperience(),
                resource.location(),
                resource.phoneNumber(),
                resource.professionalTitle(),
                resource.userId()
        );
    }
}
