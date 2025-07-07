package com.meditech.careme.platform.supportrequests.application.internal.queryservices;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.domain.model.queries.GetSupportRequestByIdQuery;
import com.meditech.careme.platform.supportrequests.domain.services.SupportRequestQueryService;
import com.meditech.careme.platform.supportrequests.infrastructure.persistence.jpa.SupportRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SupportRequestQueryService Implementation
 *
 * @summary
 * Implementation of the SupportRequestQueryService interface.
 * It handles queries related to support requests.
 *
 * @since 1.0
 */
@Service
public class SupportRequestQueryServiceImpl implements SupportRequestQueryService {

    private final SupportRequestRepository supportRequestRepository;

    public SupportRequestQueryServiceImpl(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SupportRequest> handle(GetSupportRequestByIdQuery query) {
        return supportRequestRepository.findById(query.supportRequestId());
    }
}
