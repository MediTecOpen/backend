package com.meditech.careme.platform.paymentmethods.domain.model.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * CreatePaymentMethodCommand
 *
 * @summary
 * Command to create a payment method with payment details and foreign key to CareService.
 * @since 1.0
 */
public record CreatePaymentMethodCommand(
        BigDecimal amount,
        String cardType,
        String maskedCardNumber,
        LocalDateTime paymentDateTime,
        String status,
        String description,
        UUID careServiceId
) {
    public CreatePaymentMethodCommand {
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
        if (careServiceId == null)
            throw new IllegalArgumentException("Care service ID cannot be null");
        // Note: description can be optional (nullable), so no validation
    }
}
