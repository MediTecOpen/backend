package com.meditech.careme.platform.users.interfaces.rest.transform;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.interfaces.rest.resources.UserResource;

/**
 * Assembler to create a UserResource from a User entity.
 * @since 1.0
 */
public class UserResourceFromEntityAssembler {
    /**
     * Converts a User entity to a UserResource.
     * @param entity User entity to convert
     * @return UserResource created from the entity
     */
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getUserId(),
                entity.getFirstName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRole()
        );
    }
}