package com.meditech.careme.platform.users.domain.model.queries;

/**
 * @summary
 * This class represents the query to get a user by its id.
 * @param id - the id of the user.
 */
public record GetUserByIdQuery(Long id) {
    public GetUserByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}