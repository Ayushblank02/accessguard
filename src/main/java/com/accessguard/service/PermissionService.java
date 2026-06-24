package com.accessguard.service;

import com.accessguard.domain.Permission;
import com.accessguard.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(
            PermissionRepository permissionRepository) {

        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(
            Permission permission) {

        return permissionRepository.save(permission);
    }

    public List<Permission> getAllPermissions() {

        return permissionRepository.findAll();
    }

    public Permission findByName(
            String name) {

        return permissionRepository.findByName(name)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Permission not found"));
    }
}