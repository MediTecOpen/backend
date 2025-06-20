package com.meditech.careme.platform.users.interfaces.rest.transform;

import com.meditech.careme.platform.users.domain.model.commands.CreateUserCommand;
import com.meditech.careme.platform.users.interfaces.rest.resources.CreateUserResource;

/**
 * Assembler to create a CreateUserCommand from a CreateUserResource.
 * @since 1.0
 */
public class CreateUserCommandFromResourceAssembler {
    /**
     * Converts a CreateUserResource to a CreateUserCommand.
     * @param resource CreateUserResource to convert
     * @return CreateUserCommand created from the resource
     */
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.firstName(),
                resource.email(),
                resource.password(),
                resource.phone(),
                resource.role()
        );
    }
}