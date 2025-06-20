package com.meditech.careme.platform.caregivers.application.internal.commandservices;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.domain.model.commands.CreateCaregiverCommand;
import com.meditech.careme.platform.caregivers.domain.services.CaregiverCommandService;
import com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa.CaregiverRepository;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CaregiverCommandService Implementation
 *
 * @summary
 * Implementation of the CaregiverCommandService interface.
 * It is responsible for handling caregiver-related commands.
 *
 * @since 1.0
 */
@Service
public class CaregiverCommandServiceImpl implements CaregiverCommandService {

    private final CaregiverRepository caregiverRepository;
    private final UserRepository userRepository;

    public CaregiverCommandServiceImpl(CaregiverRepository caregiverRepository, UserRepository userRepository) {
        this.caregiverRepository = caregiverRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Caregiver> handle(CreateCaregiverCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.userId() + " does not exist"));

        var caregiver = new Caregiver(command, user);
        var createdCaregiver = caregiverRepository.save(caregiver);
        return Optional.of(createdCaregiver);
    }
}
