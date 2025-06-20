package com.meditech.careme.platform.users.domain.services;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.domain.model.commands.CreateUserCommand;

import java.util.Optional;

/**
 * @name UserCommandService
 * @summary
 * This interface represents the service to handle user-related commands.
 */
public interface UserCommandService {
    /**
     * Handles the create user command.
     * @param command The create user command.
     * @return The created user.
     * @throws IllegalArgumentException If any user field is null or empty.
     * @see CreateUserCommand
     */
    Optional<User> handle(CreateUserCommand command);
}
