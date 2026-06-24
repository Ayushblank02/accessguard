package com.accessguard.controller;

import com.accessguard.domain.Role;
import com.accessguard.service.RoleService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(
            RoleService roleService) {

        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(
            @RequestBody Role role) {

        return roleService.createRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {

        return roleService.getAllRoles();
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public Role assignPermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {

        return roleService.assignPermission(
                roleId,
                permissionId);
    }
}