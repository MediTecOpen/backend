package com.meditech.careme.platform.supportrequests.domain.model.aggregates;

import com.meditech.careme.platform.supportrequests.domain.model.commands.CreateSupportRequestCommand;
import com.meditech.careme.platform.users.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * SupportRequest Aggregate Root
 *
 * @summary
 * Represents a user support request for assistance or reporting issues.
 * Includes details such as type, priority, status, and creation time.
 * @since 1.0
 */
@Entity
public class SupportRequest extends AbstractAggregateRoot<SupportRequest> {

    @Id
    @Getter
    private UUID supportRequestId;

    @Column(nullable = false)
    @Getter
    private String type;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Getter
    private String description;

    @Column(nullable = false)
    @Getter
    private String priority;

    @Column(nullable = false)
    @Getter
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Getter
    private String responseStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    private User user;

    protected SupportRequest() {}

    // ✅ Constructor adaptado con el comando y el aggregate relacionado
    public SupportRequest(CreateSupportRequestCommand command, User user) {
        this.supportRequestId = UUID.randomUUID();
        this.type = command.type();
        this.description = command.description();
        this.priority = command.priority();
        this.responseStatus = command.responseStatus();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    public void update(String type, String description, String priority, String responseStatus, User user) {
        this.type = type;
        this.description = description;
        this.priority = priority;
        this.responseStatus = responseStatus;
        this.user = user;
    }
}
