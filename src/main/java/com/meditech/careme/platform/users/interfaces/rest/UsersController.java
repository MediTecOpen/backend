package com.meditech.careme.platform.users.interfaces.rest;

import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.meditech.careme.platform.users.domain.model.queries.GetUserByIdQuery;
import com.meditech.careme.platform.users.domain.services.UserCommandService;
import com.meditech.careme.platform.users.domain.services.UserQueryService;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import com.meditech.careme.platform.users.interfaces.rest.resources.CreateUserResource;
import com.meditech.careme.platform.users.interfaces.rest.resources.UserResource;
import com.meditech.careme.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.meditech.careme.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for users.
 * @summary
 * This class provides REST endpoints for managing users.
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for managing users")
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final UserRepository userRepository;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService, UserRepository userRepository) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all users", description = "Retrieves all registered users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "204", description = "No users available")
    })
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userRepository.findAll();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get user by email", description = "Retrieves a user by their email address")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/search")
    public ResponseEntity<UserResource> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userQueryService.handle(new GetUserByEmailQuery(email));
        return user.map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided information")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid user data or user already exists")
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> createdUser = userCommandService.handle(
                CreateUserCommandFromResourceAssembler.toCommandFromResource(resource)
        );
        return createdUser.map(user -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(user), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing user", description = "Updates a user completely with new data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody CreateUserResource resource) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = existingUser.get();
        userToUpdate.updateFromCommand(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        User updatedUser = userRepository.save(userToUpdate);

        return ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser));
    }

    @Operation(summary = "Delete a user by ID", description = "Deletes an existing user with the specified ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}