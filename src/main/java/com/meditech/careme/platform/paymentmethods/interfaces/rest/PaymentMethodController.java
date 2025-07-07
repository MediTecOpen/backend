package com.meditech.careme.platform.paymentmethods.interfaces.rest;

import com.meditech.careme.platform.paymentmethods.domain.model.aggregates.PaymentMethod;
import com.meditech.careme.platform.paymentmethods.domain.model.commands.CreatePaymentMethodCommand;
import com.meditech.careme.platform.paymentmethods.domain.model.queries.GetPaymentMethodByIdQuery;
import com.meditech.careme.platform.paymentmethods.domain.services.PaymentMethodCommandService;
import com.meditech.careme.platform.paymentmethods.domain.services.PaymentMethodQueryService;
import com.meditech.careme.platform.paymentmethods.infrastructure.persistence.jpa.PaymentMethodRepository;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.resources.CreatePaymentMethodResource;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.resources.PaymentMethodResource;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.transform.CreatePaymentMethodCommandFromResourceAssembler;
import com.meditech.careme.platform.paymentmethods.interfaces.rest.transform.PaymentMethodResourceFromEntityAssembler;
import com.meditech.careme.platform.careservices.infrastructure.persistence.jpa.CareServiceRepository;
import com.meditech.careme.platform.careservices.domain.model.aggregates.CareService;

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
@RequestMapping(value = "/api/v1/paymentmethods", produces = APPLICATION_JSON_VALUE)
@Tag(name = "PaymentMethods", description = "Endpoints for managing payment methods")
public class PaymentMethodController {

    private final PaymentMethodCommandService commandService;
    private final PaymentMethodQueryService queryService;
    private final PaymentMethodRepository repository;
    private final CareServiceRepository careServiceRepository;

    public PaymentMethodController(PaymentMethodCommandService commandService,
                                   PaymentMethodQueryService queryService,
                                   PaymentMethodRepository repository,
                                   CareServiceRepository careServiceRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.repository = repository;
        this.careServiceRepository = careServiceRepository;
    }

    @GetMapping
    @Operation(summary = "Get all payment methods")
    public ResponseEntity<List<PaymentMethodResource>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = repository.findAll();
        if (paymentMethods.isEmpty()) return ResponseEntity.noContent().build();

        List<PaymentMethodResource> resources = paymentMethods.stream()
                .map(PaymentMethodResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment method by ID")
    public ResponseEntity<PaymentMethodResource> getPaymentMethodById(@PathVariable UUID id) {
        Optional<PaymentMethod> paymentMethod = queryService.handle(new GetPaymentMethodByIdQuery(id));
        return paymentMethod.map(p -> ResponseEntity.ok(PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create payment method")
    public ResponseEntity<PaymentMethodResource> createPaymentMethod(@RequestBody CreatePaymentMethodResource resource) {
        Optional<CareService> careService = careServiceRepository.findById(resource.careServiceId());
        if (careService.isEmpty()) return ResponseEntity.badRequest().build();

        CreatePaymentMethodCommand command = CreatePaymentMethodCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<PaymentMethod> created = commandService.handle(command);
        return created.map(p -> new ResponseEntity<>(PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(p), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update payment method")
    public ResponseEntity<PaymentMethodResource> updatePaymentMethod(
            @PathVariable UUID id,
            @RequestBody CreatePaymentMethodResource resource) {

        Optional<PaymentMethod> existing = repository.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Optional<CareService> careService = careServiceRepository.findById(resource.careServiceId());
        if (careService.isEmpty()) return ResponseEntity.badRequest().build();

        PaymentMethod paymentMethod = existing.get();
        paymentMethod.update(
                resource.amount(),
                resource.cardType(),
                resource.maskedCardNumber(),
                resource.paymentDateTime(),
                resource.status(),
                resource.description(),
                careService.get()
        );

        var updated = repository.save(paymentMethod);
        return ResponseEntity.ok(PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete payment method")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable UUID id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
