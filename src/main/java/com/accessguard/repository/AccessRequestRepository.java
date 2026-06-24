package com.accessguard.repository;

import com.accessguard.domain.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRequestRepository
        extends JpaRepository<AccessRequest, Long> {
}