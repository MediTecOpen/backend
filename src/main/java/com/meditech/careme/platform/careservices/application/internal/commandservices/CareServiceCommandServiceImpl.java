package com.meditech.careme.platform.careservices.application.internal.commandservices;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.domain.model.commands.CreateCareServiceCommand;
import com.meditech.careme.platform.careservices.domain.services.CareServiceCommandService;
import com.meditech.careme.platform.careservices.infrastructure.persistence.jpa.CareServiceRepository;
import com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa.CaregiverRepository;
import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CareServiceCommandService Implementation
 *
 * @summary
 * Implementation of the CareServiceCommandService interface.
 * It is responsible for handling care service-related commands.
 *
 * @since 1.0
 */
@Service
public class CareServiceCommandServiceImpl implements CareServiceCommandService {

    private final CareServiceRepository careServiceRepository;
    private final CaregiverRepository caregiverRepository;

    public CareServiceCommandServiceImpl(CareServiceRepository careServiceRepository, CaregiverRepository caregiverRepository) {
        this.careServiceRepository = careServiceRepository;
        this.caregiverRepository = caregiverRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CareService> handle(CreateCareServiceCommand command) {
        Caregiver caregiver = caregiverRepository.findById(command.caregiverId())
                .orElseThrow(() -> new IllegalArgumentException("Caregiver with ID " + command.caregiverId() + " does not exist"));

        var careService = new CareService(command, caregiver);
        var created = careServiceRepository.save(careService);
        return Optional.of(created);
    }
}
