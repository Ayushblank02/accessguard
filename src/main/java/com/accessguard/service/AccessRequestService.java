package com.accessguard.service;

import com.accessguard.domain.AccessRequest;
import com.accessguard.repository.AccessRequestRepository;
import com.accessguard.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessRequestService {

    private final AccessRequestRepository
            accessRequestRepository;

    private final UserRepository
            userRepository;

    private final AuditEventService
            auditEventService;

    public AccessRequestService(
            AccessRequestRepository accessRequestRepository,
            UserRepository userRepository,
            AuditEventService auditEventService) {

        this.accessRequestRepository =
                accessRequestRepository;

        this.userRepository =
                userRepository;

        this.auditEventService =
                auditEventService;
    }

    public AccessRequest createRequest(
            AccessRequest request) {

        AccessRequest saved =
                accessRequestRepository.save(
                        request);

        auditEventService.logEvent(
                "ACCESS_REQUEST_CREATED",
                "SYSTEM");

        return saved;
    }

    public List<AccessRequest> getAllRequests() {

        return accessRequestRepository.findAll();
    }

    public AccessRequest approveRequest(
            Long requestId) {

        AccessRequest request =
                accessRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Request not found"));

        request.setStatus("APPROVED");

        AccessRequest saved =
                accessRequestRepository.save(
                        request);

        auditEventService.logEvent(
                "ACCESS_REQUEST_APPROVED",
                "SYSTEM");

        return saved;
    }

    public AccessRequest rejectRequest(
            Long requestId) {

        AccessRequest request =
                accessRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Request not found"));

        request.setStatus("REJECTED");

        AccessRequest saved =
                accessRequestRepository.save(
                        request);

        auditEventService.logEvent(
                "ACCESS_REQUEST_REJECTED",
                "SYSTEM");

        return saved;
    }
}