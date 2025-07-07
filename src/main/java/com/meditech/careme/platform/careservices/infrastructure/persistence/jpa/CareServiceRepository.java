package com.meditech.careme.platform.careservices.infrastructure.persistence.jpa;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA repository for CareService entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for the CareService entity.
 * It uses CareService as the entity type and UUID as the ID type.
 * @since 1.0
 */
@Repository
public interface CareServiceRepository extends JpaRepository<CareService, UUID> {
}
