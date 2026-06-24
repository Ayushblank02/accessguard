package com.accessguard.controller;

import com.accessguard.domain.Permission;
import com.accessguard.service.PermissionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(
            PermissionService permissionService) {

        this.permissionService = permissionService;
    }

    @PostMapping
    public Permission createPermission(
            @RequestBody Permission permission) {

        return permissionService.createPermission(
                permission);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {

        return permissionService.getAllPermissions();
    }
}