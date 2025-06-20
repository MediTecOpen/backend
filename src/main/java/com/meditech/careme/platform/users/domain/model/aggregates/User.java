package com.meditech.careme.platform.users.domain.model.aggregates;

import com.meditech.careme.platform.users.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * User Aggregate Root
 *
 * @summary
 * The User class is an aggregate root that represents a system user.
 * It is responsible for handling the CreateUserCommand command.
 * @since 1.0
 */
@Entity
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long userId;

    @Column(nullable = false)
    @Getter
    private String firstName;

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String phone;

    @Column(nullable = false)
    @Getter
    private String role;

    protected User() {}

    public User(CreateUserCommand command) {
        this.firstName = command.firstName();
        this.email = command.email();
        this.password = command.password();
        this.phone = command.phone();
        this.role = command.role();
    }

    public void updateFromCommand(CreateUserCommand command) {
        this.firstName = command.firstName();
        this.email = command.email();
        this.password = command.password();
        this.phone = command.phone();
        this.role = command.role();
    }
}