package com.meditech.careme.platform.paymentmethods.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Resource returned for a PaymentMethod.
 * @summary
 * This record represents a completed payment for a care service.
 * @since 1.0
 */
public record PaymentMethodResource(
        UUID paymentMethodId,
        BigDecimal amount,
        String cardType,
        String maskedCardNumber,
        LocalDateTime paymentDateTime,
        String status,
        String description,
        UUID careServiceId
) {
}
