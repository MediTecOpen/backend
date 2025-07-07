package com.meditech.careme.platform.paymentmethods.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Resource record for creating a payment method.
 * @summary
 * This record represents the resource for creating a payment method.
 * It includes the payment details and careServiceId as foreign key.
 * @since 1.0
 */
public record CreatePaymentMethodResource(
        BigDecimal amount,
        String cardType,
        String maskedCardNumber,
        LocalDateTime paymentDateTime,
        String status,
        String description,
        UUID careServiceId
) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException If any field is invalid.
     */
    public CreatePaymentMethodResource {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        if (cardType == null || cardType.isBlank())
            throw new IllegalArgumentException("Card type cannot be null or empty");
        if (maskedCardNumber == null || maskedCardNumber.isBlank())
            throw new IllegalArgumentException("Masked card number cannot be null or empty");
        if (paymentDateTime == null)
            throw new IllegalArgumentException("Payment date/time cannot be null");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status cannot be null or empty");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty");
        if (careServiceId == null)
            throw new IllegalArgumentException("CareService ID cannot be null");
    }
}
