package com.meditech.careme.platform.paymentmethods.application.internal.commandservices;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.domain.model.commands.CreatePaymentMethodCommand;
import com.meditech.careme.platform.paymentmethods.domain.services.PaymentMethodCommandService;
import com.meditech.careme.platform.paymentmethods.infrastructure.persistence.jpa.PaymentMethodRepository;
import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.infrastructure.persistence.jpa.CareServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PaymentMethodCommandService Implementation
 *
 * @summary
 * Handles the creation of payment methods.
 * Validates related CareService existence before saving.
 *
 * @since 1.0
 */
@Service
public class PaymentMethodCommandServiceImpl implements PaymentMethodCommandService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final CareServiceRepository careServiceRepository;

    public PaymentMethodCommandServiceImpl(PaymentMethodRepository paymentMethodRepository,
                                           CareServiceRepository careServiceRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.careServiceRepository = careServiceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PaymentMethod> handle(CreatePaymentMethodCommand command) {
        CareService careService = careServiceRepository.findById(command.careServiceId())
                .orElseThrow(() -> new IllegalArgumentException("CareService with ID " + command.careServiceId() + " does not exist"));

        var paymentMethod = new PaymentMethod(command, careService);
        var created = paymentMethodRepository.save(paymentMethod);
        return Optional.of(created);
    }
}
