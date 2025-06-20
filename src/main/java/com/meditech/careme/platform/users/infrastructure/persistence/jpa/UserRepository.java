package com.meditech.careme.platform.users.infrastructure.persistence.jpa;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for User entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for the User entity.
 * It uses User as the entity type and Long as the ID type.
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by email.
     * @param email Email of the user
     * @return Optional user
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists by email.
     * @param email Email of the user
     * @return True if exists, false otherwise
     */
    boolean existsByEmail(String email);
}