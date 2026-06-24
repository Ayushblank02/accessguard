package com.accessguard.controller;

import com.accessguard.domain.AuditEvent;
import com.accessguard.service.AuditEventService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-events")
public class AuditEventController {

    private final AuditEventService
            auditEventService;

    public AuditEventController(
            AuditEventService auditEventService) {

        this.auditEventService =
                auditEventService;
    }

    @GetMapping
    public List<AuditEvent> getAllEvents() {

        return auditEventService.getAllEvents();
    }
}
