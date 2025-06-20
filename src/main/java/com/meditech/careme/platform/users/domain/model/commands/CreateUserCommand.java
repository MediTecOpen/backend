package com.meditech.careme.platform.users.domain.model.commands;

/**
 * CreateUserCommand
 * @summary
 * CreateUserCommand is a record class that represents the command to create a user.
 */
public record CreateUserCommand(String firstName, String email, String password, String phone, String role) {

    public CreateUserCommand {
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