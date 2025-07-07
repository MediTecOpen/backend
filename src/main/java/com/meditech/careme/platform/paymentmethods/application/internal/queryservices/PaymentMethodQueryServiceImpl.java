package com.meditech.careme.platform.paymentmethods.application.internal.queryservices;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.domain.model.queries.GetPaymentMethodByIdQuery;
import com.meditech.careme.platform.paymentmethods.domain.services.PaymentMethodQueryService;
import com.meditech.careme.platform.paymentmethods.infrastructure.persistence.jpa.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PaymentMethodQueryService Implementation
 *
 * @summary
 * Implementation of the PaymentMethodQueryService interface.
 * Handles queries related to payment methods.
 *
 * @since 1.0
 */
@Service
public class PaymentMethodQueryServiceImpl implements PaymentMethodQueryService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodQueryServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PaymentMethod> handle(GetPaymentMethodByIdQuery query) {
        return paymentMethodRepository.findById(query.paymentMethodId());
    }
}
