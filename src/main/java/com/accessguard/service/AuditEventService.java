package com.accessguard.service;

import com.accessguard.domain.AuditEvent;
import com.accessguard.repository.AuditEventRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditEventService {

    private final AuditEventRepository
            auditEventRepository;

    public AuditEventService(
            AuditEventRepository auditEventRepository) {

        this.auditEventRepository =
                auditEventRepository;
    }

    public AuditEvent logEvent(
            String action,
            String actor) {

        AuditEvent event =
                new AuditEvent();

        event.setAction(action);
        event.setActor(actor);

        return auditEventRepository.save(
                event);
    }

    public List<AuditEvent> getAllEvents() {

        return auditEventRepository.findAll();
    }
}