package com.meditech.careme.platform.caregivers.interfaces.rest;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.caregivers.domain.model.commands.CreateCaregiverCommand;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiverByIdQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversBySpecialtyQuery;
import com.meditech.careme.platform.caregivers.domain.model.queries.GetCaregiversByYearsOfExperienceQuery;
import com.meditech.careme.platform.caregivers.domain.services.CaregiverCommandService;
import com.meditech.careme.platform.caregivers.domain.services.CaregiverQueryService;
import com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa.CaregiverRepository;
import com.meditech.careme.platform.caregivers.interfaces.rest.resources.CaregiverResource;
import com.meditech.careme.platform.caregivers.interfaces.rest.resources.CreateCaregiverResource;
import com.meditech.careme.platform.caregivers.interfaces.rest.transform.CaregiverResourceFromEntityAssembler;
import com.meditech.careme.platform.caregivers.interfaces.rest.transform.CreateCaregiverCommandFromResourceAssembler;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/caregivers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Caregivers", description = "Endpoints for managing caregivers")
public class CaregiversController {

    private final CaregiverCommandService commandService;
    private final CaregiverQueryService queryService;
    private final CaregiverRepository repository;
    private final UserRepository userRepository;

    public CaregiversController(CaregiverCommandService commandService,
                                CaregiverQueryService queryService,
                                CaregiverRepository repository,
                                UserRepository userRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @Operation(summary = "Get all caregivers")
    public ResponseEntity<List<CaregiverResource>> getAllCaregivers() {
        List<Caregiver> caregivers = repository.findAll();
        if (caregivers.isEmpty()) return ResponseEntity.noContent().build();

        List<CaregiverResource> resources = caregivers.stream()
                .map(CaregiverResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get caregiver by ID")
    public ResponseEntity<CaregiverResource> getCaregiverById(@PathVariable UUID id) {
        Optional<Caregiver> caregiver = queryService.handle(new GetCaregiverByIdQuery(id));
        return caregiver.map(c -> ResponseEntity.ok(CaregiverResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/specialty")
    @Operation(summary = "Get caregivers by specialty")
    public ResponseEntity<List<CaregiverResource>> getCaregiversBySpecialty(@RequestParam String specialty) {
        List<Caregiver> caregivers = queryService.handle(new GetCaregiversBySpecialtyQuery(specialty));
        if (caregivers.isEmpty()) return ResponseEntity.noContent().build();

        List<CaregiverResource> resources = caregivers.stream()
                .map(CaregiverResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/experience")
    @Operation(summary = "Get caregivers by years of experience")
    public ResponseEntity<List<CaregiverResource>> getCaregiversByExperience(@RequestParam int years) {
        List<Caregiver> caregivers = queryService.handle(new GetCaregiversByYearsOfExperienceQuery(years));
        if (caregivers.isEmpty()) return ResponseEntity.noContent().build();

        List<CaregiverResource> resources = caregivers.stream()
                .map(CaregiverResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @Operation(summary = "Create caregiver")
    public ResponseEntity<CaregiverResource> createCaregiver(@RequestBody CreateCaregiverResource resource) {
        Optional<User> user = userRepository.findById(resource.userId());
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        CreateCaregiverCommand command = CreateCaregiverCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Caregiver> created = commandService.handle(command);
        return created.map(c -> new ResponseEntity<>(CaregiverResourceFromEntityAssembler.toResourceFromEntity(c), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update caregiver")
    public ResponseEntity<CaregiverResource> updateCaregiver(
            @PathVariable UUID id,
            @RequestBody CreateCaregiverResource resource) {

        Optional<Caregiver> existing = repository.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Optional<User> user = userRepository.findById(resource.userId());
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        Caregiver caregiver = existing.get();
        caregiver.updateFromCommand(
                CreateCaregiverCommandFromResourceAssembler.toCommandFromResource(resource),
                user.get()
        );
        var updated = repository.save(caregiver);
        return ResponseEntity.ok(CaregiverResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete caregiver")
    public ResponseEntity<Void> deleteCaregiver(@PathVariable UUID id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
