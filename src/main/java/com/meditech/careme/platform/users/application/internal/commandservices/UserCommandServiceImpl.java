package com.meditech.careme.platform.users.application.internal.commandservices;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.domain.model.commands.CreateUserCommand;
import com.meditech.careme.platform.users.domain.services.UserCommandService;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserCommandService Implementation
 *
 * @summary
 * Implementation of the UserCommandService interface.
 * It is responsible for handling user-related commands.
 *
 * @since 1.0
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new IllegalArgumentException("A user with this email already exists");

        var user = new User(command);
        var createdUser = userRepository.save(user);
        return Optional.of(createdUser);
    }
}
