package com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * JPA repository for Caregiver entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for the Caregiver entity.
 * It uses Caregiver as the entity type and UUID as the ID type.
 * @since 1.0
 */
@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    /**
     * Find caregivers by specialty.
     * @param specialty The caregiver's specialty
     * @return List of caregivers with the given specialty
     */
    List<Caregiver> findAllBySpecialty(String specialty);

    /**
     * Find caregivers by years of experience.
     * @param yearsOfExperience The number of years
     * @return List of caregivers with the given years of experience
     */
    List<Caregiver> findAllByYearsOfExperience(Integer yearsOfExperience);
}
