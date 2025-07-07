package com.meditech.careme.platform.supportrequests.interfaces.rest;

import com.meditech.careme.platform.supportrequests.domain.model.aggregates.SupportRequest;
import com.meditech.careme.platform.supportrequests.domain.model.commands.CreateSupportRequestCommand;
import com.meditech.careme.platform.supportrequests.domain.model.queries.GetSupportRequestByIdQuery;
import com.meditech.careme.platform.supportrequests.domain.services.SupportRequestCommandService;
import com.meditech.careme.platform.supportrequests.domain.services.SupportRequestQueryService;
import com.meditech.careme.platform.supportrequests.infrastructure.persistence.jpa.SupportRequestRepository;
import com.meditech.careme.platform.supportrequests.interfaces.rest.resources.CreateSupportRequestResource;
import com.meditech.careme.platform.supportrequests.interfaces.rest.resources.SupportRequestResource;
import com.meditech.careme.platform.supportrequests.interfaces.rest.transform.CreateSupportRequestCommandFromResourceAssembler;
import com.meditech.careme.platform.supportrequests.interfaces.rest.transform.SupportRequestResourceFromEntityAssembler;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import com.meditech.careme.platform.users.infrastructure.persistence.jpa.UserRepository;

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
@RequestMapping(value = "/api/v1/supportrequests", produces = APPLICATION_JSON_VALUE)
@Tag(name = "SupportRequests", description = "Endpoints for managing support requests")
public class SupportRequestsController {

    private final SupportRequestCommandService commandService;
    private final SupportRequestQueryService queryService;
    private final SupportRequestRepository repository;
    private final UserRepository userRepository;

    public SupportRequestsController(SupportRequestCommandService commandService,
                                     SupportRequestQueryService queryService,
                                     SupportRequestRepository repository,
                                     UserRepository userRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @Operation(summary = "Get all support requests")
    public ResponseEntity<List<SupportRequestResource>> getAllSupportRequests() {
        List<SupportRequest> supportRequests = repository.findAll();
        if (supportRequests.isEmpty()) return ResponseEntity.noContent().build();

        List<SupportRequestResource> resources = supportRequests.stream()
                .map(SupportRequestResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get support request by ID")
    public ResponseEntity<SupportRequestResource> getSupportRequestById(@PathVariable UUID id) {
        Optional<SupportRequest> supportRequest = queryService.handle(new GetSupportRequestByIdQuery(id));
        return supportRequest.map(s -> ResponseEntity.ok(SupportRequestResourceFromEntityAssembler.toResourceFromEntity(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create support request")
    public ResponseEntity<SupportRequestResource> createSupportRequest(@RequestBody CreateSupportRequestResource resource) {
        Optional<User> user = userRepository.findById(resource.userId());
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        CreateSupportRequestCommand command = CreateSupportRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<SupportRequest> created = commandService.handle(command);
        return created.map(s -> new ResponseEntity<>(SupportRequestResourceFromEntityAssembler.toResourceFromEntity(s), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update support request")
    public ResponseEntity<SupportRequestResource> updateSupportRequest(
            @PathVariable UUID id,
            @RequestBody CreateSupportRequestResource resource) {

        Optional<SupportRequest> existing = repository.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Optional<User> user = userRepository.findById(resource.userId());
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        SupportRequest supportRequest = existing.get();
        supportRequest.update(
                resource.type(),
                resource.description(),
                resource.priority(),
                resource.responseStatus(),
                user.get()
        );
        var updated = repository.save(supportRequest);
        return ResponseEntity.ok(SupportRequestResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete support request")
    public ResponseEntity<Void> deleteSupportRequest(@PathVariable UUID id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
