package com.meditech.careme.platform.caregivers.interfaces.rest.transform;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.interfaces.rest.resources.CaregiverResource;

/**
 * Assembler to create a CaregiverResource from a Caregiver entity.
 * @since 1.0
 */
public class CaregiverResourceFromEntityAssembler {
    /**
     * Converts a Caregiver entity to a CaregiverResource.
     * @param entity Caregiver entity to convert
     * @return CaregiverResource created from the entity
     */
    public static CaregiverResource toResourceFromEntity(Caregiver entity) {
        return new CaregiverResource(
                entity.getCaregiverId(),
                entity.getName(),
                entity.getAge(),
                entity.getSpecialty(),
                entity.getYearsOfExperience(),
                entity.getLocation(),
                entity.getPhoneNumber(),
                entity.getProfessionalTitle(),
                entity.getUser().getUserId()
        );
    }
}
