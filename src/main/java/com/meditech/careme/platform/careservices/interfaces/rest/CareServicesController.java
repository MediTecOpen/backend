package com.meditech.careme.platform.careservices.interfaces.rest;

import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;
import com.meditech.careme.platform.careservices.domain.model.commands.CreateCareServiceCommand;
import com.meditech.careme.platform.careservices.domain.model.queries.GetCareServiceByIdQuery;
import com.meditech.careme.platform.careservices.domain.services.CareServiceCommandService;
import com.meditech.careme.platform.careservices.domain.services.CareServiceQueryService;
import com.meditech.careme.platform.careservices.infrastructure.persistence.jpa.CareServiceRepository;
import com.meditech.careme.platform.careservices.interfaces.rest.resources.CareServiceResource;
import com.meditech.careme.platform.careservices.interfaces.rest.resources.CreateCareServiceResource;
import com.meditech.careme.platform.careservices.interfaces.rest.transform.CareServiceResourceFromEntityAssembler;
import com.meditech.careme.platform.careservices.interfaces.rest.transform.CreateCareServiceCommandFromResourceAssembler;
import com.meditech.careme.platform.caregivers.infrastructure.persistence.jpa.CaregiverRepository;
import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/careservices", produces = APPLICATION_JSON_VALUE)
@Tag(name = "CareServices", description = "Endpoints for managing care services")
public class CareServicesController {

    private final CareServiceCommandService commandService;
    private final CareServiceQueryService queryService;
    private final CareServiceRepository repository;
    private final CaregiverRepository caregiverRepository;

    public CareServicesController(CareServiceCommandService commandService,
                                  CareServiceQueryService queryService,
                                  CareServiceRepository repository,
                                  CaregiverRepository caregiverRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.repository = repository;
        this.caregiverRepository = caregiverRepository;
    }

    @GetMapping
    @Operation(summary = "Get all care services")
    public ResponseEntity<List<CareServiceResource>> getAllCareServices() {
        List<CareService> careServices = repository.findAll();
        if (careServices.isEmpty()) return ResponseEntity.noContent().build();

        List<CareServiceResource> resources = careServices.stream()
                .map(CareServiceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get care service by ID")
    public ResponseEntity<CareServiceResource> getCareServiceById(@PathVariable UUID id) {
        Optional<CareService> careService = queryService.handle(new GetCareServiceByIdQuery(id));
        return careService.map(c -> ResponseEntity.ok(CareServiceResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create care service")
    public ResponseEntity<CareServiceResource> createCareService(@RequestBody CreateCareServiceResource resource) {
        Optional<Caregiver> caregiver = caregiverRepository.findById(resource.caregiverId());
        if (caregiver.isEmpty()) return ResponseEntity.badRequest().build();

        CreateCareServiceCommand command = CreateCareServiceCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<CareService> created = commandService.handle(command);
        return created.map(c -> new ResponseEntity<>(CareServiceResourceFromEntityAssembler.toResourceFromEntity(c), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update care service")
    public ResponseEntity<CareServiceResource> updateCareService(
            @PathVariable UUID id,
            @RequestBody CreateCareServiceResource resource) {

        Optional<CareService> existing = repository.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Optional<Caregiver> caregiver = caregiverRepository.findById(resource.caregiverId());
        if (caregiver.isEmpty()) return ResponseEntity.badRequest().build();

        CareService careService = existing.get();
        careService.update(
                resource.title(),
                resource.patientName(),
                resource.location(),
                resource.tasks(),
                resource.startTime(),
                resource.endTime(),
                resource.status(),
                caregiver.get()
        );
        var updated = repository.save(careService);
        return ResponseEntity.ok(CareServiceResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete care service")
    public ResponseEntity<Void> deleteCareService(@PathVariable UUID id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
