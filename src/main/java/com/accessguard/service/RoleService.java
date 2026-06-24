package com.accessguard.service;

import com.accessguard.domain.Permission;
import com.accessguard.domain.Role;
import com.accessguard.repository.PermissionRepository;
import com.accessguard.repository.RoleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public Role createRole(
            Role role) {

        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {

        return roleRepository.findAll();
    }

    public Role findByName(
            String name) {

        return roleRepository.findByName(name)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Role not found"));
    }

    public Role assignPermission(
            Long roleId,
            Long permissionId) {

        Role role =
                roleRepository.findById(roleId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Role not found"));

        Permission permission =
                permissionRepository.findById(permissionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Permission not found"));

        role.getPermissions().add(permission);

        return roleRepository.save(role);
    }
}