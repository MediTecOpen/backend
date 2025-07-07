package com.meditech.careme.platform.careservices.application.internal.queryservices;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.domain.model.queries.GetCareServiceByIdQuery;
import com.meditech.careme.platform.careservices.domain.services.CareServiceQueryService;
import com.meditech.careme.platform.careservices.infrastructure.persistence.jpa.CareServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CareServiceQueryService Implementation
 *
 * @summary
 * Implementation of the CareServiceQueryService interface.
 * It handles queries related to care services.
 *
 * @since 1.0
 */
@Service
public class CareServiceQueryServiceImpl implements CareServiceQueryService {

    private final CareServiceRepository careServiceRepository;

    public CareServiceQueryServiceImpl(CareServiceRepository careServiceRepository) {
        this.careServiceRepository = careServiceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CareService> handle(GetCareServiceByIdQuery query) {
        return careServiceRepository.findById(query.careServiceId());
    }
}
