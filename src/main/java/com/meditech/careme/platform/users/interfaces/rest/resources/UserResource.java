package com.meditech.careme.platform.users.interfaces.rest.resources;

/**
 * Resource record for a user.
 * @summary
 * This record represents the resource for a user.
 * It contains the ID, first name, email, phone, and role.
 * @since 1.0
 */
public record UserResource(Long id, String firstName, String email, String phone, String role) {
}