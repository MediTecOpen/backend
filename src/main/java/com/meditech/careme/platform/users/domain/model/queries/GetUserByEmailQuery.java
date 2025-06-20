package com.meditech.careme.platform.users.domain.model.queries;

/**
 * @summary
 * This class represents the query to get a user by their email.
 * @param email - the email of the user.
 */
public record GetUserByEmailQuery(String email) {
    public GetUserByEmailQuery {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}