package com.meditech.careme.platform.users.interfaces.rest.resources;

/**
 * Resource record for creating a user.
 * @summary
 * This record represents the resource for creating a user.
 * It contains the user's first name, email, password, phone, and role.
 * @since 1.0
 */
public record CreateUserResource(String firstName, String email, String password, String phone, String role) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException If any field is null or empty.
     */
    public CreateUserResource {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("firstName cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("email cannot be null or empty");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("password cannot be null or empty");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("phone cannot be null or empty");
        if (role == null || role.isBlank())
            throw new IllegalArgumentException("role cannot be null or empty");
    }
}