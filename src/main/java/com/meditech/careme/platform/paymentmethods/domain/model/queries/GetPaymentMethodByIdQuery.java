package com.meditech.careme.platform.paymentmethods.domain.model.queries;

import java.util.UUID;

/**
 * @summary
 * This class represents the query to get a payment method by its UUID.
 * @param paymentMethodId - the UUID of the payment method.
 */
public record GetPaymentMethodByIdQuery(UUID paymentMethodId) {
    public GetPaymentMethodByIdQuery {
        if (paymentMethodId == null) {
            throw new IllegalArgumentException("PaymentMethodId cannot be null");
        }
    }
}
