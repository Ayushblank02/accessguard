package com.accessguard.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "audit_events")
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private String actor;

    private Instant createdAt =
            Instant.now();

    public AuditEvent() {
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(
            String action) {
        this.action = action;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(
            String actor) {
        this.actor = actor;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}