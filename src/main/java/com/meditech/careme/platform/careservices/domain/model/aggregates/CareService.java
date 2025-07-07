package com.meditech.careme.platform.careservices.domain.model.aggregates;

import com.meditech.careme.platform.caregivers.domain.model.aggregates.Caregiver;
import com.meditech.careme.platform.careservices.domain.model.commands.CreateCareServiceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.sql.Time;
import java.util.UUID;

/**
 * CareService Aggregate Root
 *
 * @summary
 * Represents a scheduled care service assigned to a caregiver.
 * Includes time, location, patient, and required tasks.
 * @since 1.0
 */
@Entity
public class CareService extends AbstractAggregateRoot<CareService> {

    @Id
    @Getter
    private UUID careServiceId;

    @Column(nullable = false)
    @Getter
    private String title;

    @Column(nullable = false)
    @Getter
    private String patientName;

    @Column(nullable = false)
    @Getter
    private String location;

    @Column(nullable = false)
    @Getter
    private String tasks;

    @Column(nullable = false)
    @Getter
    private Time startTime;

    @Column(nullable = false)
    @Getter
    private Time endTime;

    @Column(nullable = false)
    @Getter
    private String status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    @Getter
    private Caregiver caregiver;

    protected CareService() {}

    //   Constructor adaptado con el comando y el aggregate relacionado
    public CareService(CreateCareServiceCommand command, Caregiver caregiver) {
        this.careServiceId = UUID.randomUUID();
        this.title = command.title();
        this.patientName = command.patientName();
        this.location = command.location();
        this.tasks = command.tasks();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.status = command.status();
        this.caregiver = caregiver;
    }

    public void update(String title, String patientName, String location, String tasks,
                       Time startTime, Time endTime, String status, Caregiver caregiver) {
        this.title = title;
        this.patientName = patientName;
        this.location = location;
        this.tasks = tasks;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.caregiver = caregiver;
    }
}
