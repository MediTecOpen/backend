package com.meditech.careme.platform.paymentmethods.interfaces.rest.transform;

import com.meditech.careme.platform.paymentmethods.domain.model.commands.CreatePaymentMethodCommand;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.resources.CreatePaymentMethodResource;

/**
 * Assembler to create a CreatePaymentMethodCommand from a CreatePaymentMethodResource.
 * @since 1.0
 */
public class CreatePaymentMethodCommandFromResourceAssembler {
    /**
     * Converts a CreatePaymentMethodResource to a CreatePaymentMethodCommand.
     * @param resource CreatePaymentMethodResource to convert
     * @return CreatePaymentMethodCommand created from the resource
     */
    public static CreatePaymentMethodCommand toCommandFromResource(CreatePaymentMethodResource resource) {
        return new CreatePaymentMethodCommand(
                resource.amount(),
                resource.cardType(),
                resource.maskedCardNumber(),
                resource.paymentDateTime(),
                resource.status(),
                resource.description(),
                resource.careServiceId()
        );
    }
}
