package com.accessguard.service;

import com.accessguard.domain.Role;
import com.accessguard.domain.User;
import com.accessguard.repository.RoleRepository;
import com.accessguard.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public boolean authenticate(
            String username,
            String password) {

        User user = findByUsername(username);

        return passwordEncoder.matches(
                password,
                user.getPasswordHash());
    }

    public User assignRole(
            Long userId,
            Long roleId) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        Role role =
                roleRepository.findById(roleId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Role not found"));

        user.getRoles().add(role);

        return userRepository.save(user);
    }
}