package com.meditech.careme.platform.caregivers.application.internal.queryservices;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiverByIdQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversBySpecialtyQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversByYearsOfExperienceQuery;
import com.meditech.careme.platform.caregivers.domain.services.CaregiverQueryService;
import com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa.CaregiverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CaregiverQueryService Implementation
 *
 * @summary
 * Implementation of the CaregiverQueryService interface.
 * It is responsible for handling caregiver-related queries.
 *
 * @since 1.0
 */
@Service
public class CaregiverQueryServiceImpl implements CaregiverQueryService {

    private final CaregiverRepository caregiverRepository;

    public CaregiverQueryServiceImpl(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Caregiver> handle(GetCaregiverByIdQuery query) {
        return caregiverRepository.findById(query.caregiverId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Caregiver> handle(GetCaregiversBySpecialtyQuery query) {
        return caregiverRepository.findAllBySpecialty(query.specialty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Caregiver> handle(GetCaregiversByYearsOfExperienceQuery query) {
        return caregiverRepository.findAllByYearsOfExperience(query.yearsOfExperience());
    }
}
