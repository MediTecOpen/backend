package com.meditech.careme.platform.paymentmethods.interfaces.rest.transform;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.resources.PaymentMethodResource;

/**
 * Assembler to create a PaymentMethodResource from a PaymentMethod entity.
 * @since 1.0
 */
public class PaymentMethodResourceFromEntityAssembler {
    /**
     * Converts a PaymentMethod entity to a PaymentMethodResource.
     * @param entity PaymentMethod entity to convert
     * @return PaymentMethodResource created from the entity
     */
    public static PaymentMethodResource toResourceFromEntity(PaymentMethod entity) {
        return new PaymentMethodResource(
                entity.getPaymentMethodId(),
                entity.getAmount(),
                entity.getCardType(),
                entity.getMaskedCardNumber(),
                entity.getPaymentDateTime(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getCareService().getCareServiceId()
        );
    }
}
