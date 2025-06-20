package com.meditech.careme.platform.users.domain.services;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByIdQuery;

import java.util.Optional;

/**
 * @name UserQueryService
 * @summary
 * This interface represents the service to handle user-related queries.
 * @since 1.0.0
 */
public interface UserQueryService {
    /**
     * Handles the get user by ID query.
     * @param query The get user by ID query.
     * @return The user if exists.
     * @throws IllegalArgumentException If ID is null.
     * @see GetUserByIdQuery
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handles the get user by email query.
     * @param query The get user by email query.
     * @return The user if exists.
     * @throws IllegalArgumentException If email is null or empty.
     * @see GetUserByEmailQuery
     */
    Optional<User> handle(GetUserByEmailQuery query);
}
