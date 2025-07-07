package com.meditech.careme.platform.supportrequests.application.internal.commandservices;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.domain.model.commands.CreateSupportRequestCommand;
import com.meditech.careme.platform.supportrequests.domain.services.SupportRequestCommandService;
import com.meditech.careme.platform.supportrequests.infrastructure.persistence.jpa.SupportRequestRepository;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SupportRequestCommandService Implementation
 *
 * @summary
 * Implementation of the SupportRequestCommandService interface.
 * It is responsible for handling support request-related commands.
 *
 * @since 1.0
 */
@Service
public class SupportRequestCommandServiceImpl implements SupportRequestCommandService {

    private final SupportRequestRepository supportRequestRepository;
    private final UserRepository userRepository;

    public SupportRequestCommandServiceImpl(SupportRequestRepository supportRequestRepository, UserRepository userRepository) {
        this.supportRequestRepository = supportRequestRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SupportRequest> handle(CreateSupportRequestCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + command.userId() + " does not exist"));

        var supportRequest = new SupportRequest(command, user);
        var created = supportRequestRepository.save(supportRequest);
        return Optional.of(created);
    }
}
