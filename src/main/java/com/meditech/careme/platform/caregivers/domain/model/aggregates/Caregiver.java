package com.meditech.careme.platform.caregivers.domain.model.aggregates;

import com.meditech.careme.platform.caregivers.domain.model.commands.CreateCaregiverCommand;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.UUID;

/**
 * Caregiver Aggregate Root
 *
 * @summary
 * The Caregiver class is an aggregate root that represents a professional caregiver.
 * It is responsible for handling the CreateCaregiverCommand command.
 * @since 1.0
 */
@Entity
public class Caregiver extends AbstractAggregateRoot<Caregiver> {

    @Id
    @Getter
    private UUID caregiverId;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private Integer age;

    @Column(nullable = false)
    @Getter
    private String specialty;

    @Column(nullable = false)
    @Getter
    private Integer yearsOfExperience;

    @Column(nullable = false)
    @Getter
    private String location;

    @Column(nullable = false)
    @Getter
    private String phoneNumber;

    @Column(nullable = false)
    @Getter
    private String professionalTitle;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    private User user;

    protected Caregiver() {}

    public Caregiver(CreateCaregiverCommand command, User user) {
        this.caregiverId = UUID.randomUUID();
        this.name = command.name();
        this.age = command.age();
        this.specialty = command.specialty();
        this.yearsOfExperience = command.yearsOfExperience();
        this.location = command.location();
        this.phoneNumber = command.phoneNumber();
        this.professionalTitle = command.professionalTitle();
        this.user = user;
    }

    /**
     * Updates the caregiver using the provided command.
     * @param command the command with updated caregiver data
     * @param user the associated user entity
     */
    public void updateFromCommand(CreateCaregiverCommand command, User user) {
        this.name = command.name();
        this.age = command.age();
        this.specialty = command.specialty();
        this.yearsOfExperience = command.yearsOfExperience();
        this.location = command.location();
        this.phoneNumber = command.phoneNumber();
        this.professionalTitle = command.professionalTitle();
        this.user = user;
    }
}
