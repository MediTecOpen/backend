package com.meditech.careme.platform.caregivers.domain.model.queries;

/**
 * @summary
 * This class represents the query to get caregivers by years of experience.
 * @param yearsOfExperience - the number of years of experience.
 */
public record GetCaregiversByYearsOfExperienceQuery(Integer yearsOfExperience) {
    public GetCaregiversByYearsOfExperienceQuery {
        if (yearsOfExperience == null || yearsOfExperience < 0) {
            throw new IllegalArgumentException("Years of experience cannot be null or negative");
        }
    }
}