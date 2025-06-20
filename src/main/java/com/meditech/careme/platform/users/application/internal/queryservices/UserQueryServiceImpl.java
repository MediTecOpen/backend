package com.meditech.careme.platform.users.application.internal.queryservices;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByIdQuery;
import com.meditech.careme.platform.users.domain.services.UserQueryService;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserQueryService Implementation
 *
 * @summary
 * Implementation of the UserQueryService interface.
 * It is responsible for handling user-related queries.
 *
 * @since 1.0
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}