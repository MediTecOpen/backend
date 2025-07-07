package com.meditech.careme.platform.paymentmethods.domain.services;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.domain.model.commands.CreatePaymentMethodCommand;

import java.util.Optional;

/**
 * @name PaymentMethodCommandService
 * @summary
 * This interface represents the service to handle payment method-related commands.
 */
public interface PaymentMethodCommandService {
    /**
     * Handles the create payment method command.
     * @param command The create payment method command.
     * @return The created payment method.
     * @throws IllegalArgumentException If any field is null or invalid.
     * @see CreatePaymentMethodCommand
     */
    Optional<PaymentMethod> handle(CreatePaymentMethodCommand command);
}
