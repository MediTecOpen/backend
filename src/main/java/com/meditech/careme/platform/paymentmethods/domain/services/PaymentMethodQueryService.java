package com.meditech.careme.platform.paymentmethods.domain.services;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.domain.model.queries.GetPaymentMethodByIdQuery;

import java.util.Optional;

/**
 * @name PaymentMethodQueryService
 * @summary
 * This interface represents the service to handle payment method-related queries.
 * @since 1.0.0
 */
public interface PaymentMethodQueryService {
    /**
     * Handles the get payment method by ID query.
     * @param query The get payment method by ID query.
     * @return The payment method if it exists.
     * @throws IllegalArgumentException If ID is null.
     * @see GetPaymentMethodByIdQuery
     */
    Optional<PaymentMethod> handle(GetPaymentMethodByIdQuery query);
}
