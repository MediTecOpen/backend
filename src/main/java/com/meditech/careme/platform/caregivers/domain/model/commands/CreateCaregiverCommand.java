package com.meditech.careme.platform.caregivers.domain.model.commands;

/**
 * CreateCaregiverCommand
 * @summary
 * Command to create a caregiver using primitive types and a foreign key (userId).
 */
public record CreateCaregiverCommand(
        String name,
        int age,
        String specialty,
        int yearsOfExperience,
        String location,
        String phoneNumber,
        String professionalTitle,
        Long userId
) {
    public CreateCaregiverCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (age <= 0)
            throw new IllegalArgumentException("Age must be greater than 0");
        if (specialty == null || specialty.isBlank())
            throw new IllegalArgumentException("Specialty cannot be null or empty");
        if (yearsOfExperience < 0)
            throw new IllegalArgumentException("Years of experience cannot be negative");
        if (location == null || location.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (professionalTitle == null || professionalTitle.isBlank())
            throw new IllegalArgumentException("Professional title cannot be null or empty");
        if (userId == null)
            throw new IllegalArgumentException("User ID cannot be null");
    }
}
