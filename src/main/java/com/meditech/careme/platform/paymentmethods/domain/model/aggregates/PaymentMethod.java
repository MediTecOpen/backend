package com.meditech.careme.platform.paymentmethods.domain.model.aggregates;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.paymentmethods.domain.model.commands.CreatePaymentMethodCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PaymentMethod Aggregate Root
 *
 * @summary
 * Represents a payment made for a care service.
 * Includes amount, card type, masked number, status, and description.
 * @since 1.0
 */
@Entity
public class PaymentMethod extends AbstractAggregateRoot<PaymentMethod> {

    @Id
    @Getter
    private UUID paymentMethodId;

    @Column(nullable = false)
    @Getter
    private BigDecimal amount;

    @Column(nullable = false)
    @Getter
    private String cardType;

    @Column(nullable = false)
    @Getter
    private String maskedCardNumber;

    @Column(nullable = false)
    @Getter
    private LocalDateTime paymentDateTime;

    @Column(nullable = false)
    @Getter
    private String status;

    @Column
    @Getter
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_service_id", nullable = false)
    @Getter
    private CareService careService;

    protected PaymentMethod() {}

    public PaymentMethod(CreatePaymentMethodCommand command, CareService careService) {
        this.paymentMethodId = UUID.randomUUID();
        this.amount = command.amount();
        this.cardType = command.cardType();
        this.maskedCardNumber = command.maskedCardNumber();
        this.paymentDateTime = command.paymentDateTime();
        this.status = command.status();
        this.description = command.description();
        this.careService = careService;
    }

    public void update(BigDecimal amount, String cardType, String maskedCardNumber,
                       LocalDateTime paymentDateTime, String status, String description,
                       CareService careService) {
        this.amount = amount;
        this.cardType = cardType;
        this.maskedCardNumber = maskedCardNumber;
        this.paymentDateTime = paymentDateTime;
        this.status = status;
        this.description = description;
        this.careService = careService;
    }
}
