package com.accessguard.controller;

import com.accessguard.domain.AccessRequest;
import com.accessguard.service.AccessRequestService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access-requests")
public class AccessRequestController {

    private final AccessRequestService
            accessRequestService;

    public AccessRequestController(
            AccessRequestService accessRequestService) {

        this.accessRequestService =
                accessRequestService;
    }

    @PostMapping
    public AccessRequest createRequest(
            @RequestBody AccessRequest request) {

        return accessRequestService
                .createRequest(request);
    }

    @GetMapping
    public List<AccessRequest> getAllRequests() {

        return accessRequestService
                .getAllRequests();
    }

    @PostMapping("/{requestId}/approve")
    public AccessRequest approveRequest(
            @PathVariable Long requestId) {

        return accessRequestService
                .approveRequest(requestId);
    }

    @PostMapping("/{requestId}/reject")
    public AccessRequest rejectRequest(
            @PathVariable Long requestId) {

        return accessRequestService
                .rejectRequest(requestId);
    }
}