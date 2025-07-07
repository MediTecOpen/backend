package com.meditech.careme.platform.paymentmethods.infrastructure.persistence.jpa;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA repository for PaymentMethod entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for the PaymentMethod entity.
 * It uses PaymentMethod as the entity type and UUID as the ID type.
 * @since 1.0
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
}
