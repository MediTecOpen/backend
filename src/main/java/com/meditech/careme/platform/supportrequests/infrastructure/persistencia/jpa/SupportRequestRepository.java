package com.meditech.careme.platform.supportrequests.infrastructure.persistence.jpa;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA repository for SupportRequest entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for the SupportRequest entity.
 * It uses SupportRequest as the entity type and UUID as the ID type.
 * @since 1.0
 */
@Repository
public interface SupportRequestRepository extends JpaRepository<SupportRequest, UUID> {
}
