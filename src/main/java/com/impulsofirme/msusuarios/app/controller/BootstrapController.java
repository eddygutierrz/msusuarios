package com.impulsofirme.msusuarios.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;
import com.impulsofirme.msusuarios.app.repository.UserRepository;

@RestController
@RequestMapping("/api/users/bootstrap")
public class BootstrapController {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BootstrapController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> crearAdmin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(defaultValue = "SISTEMAS") String role,
            @RequestParam(defaultValue = "ACTIVE") String status) {

        if (repo.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Usuario ya existe");
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(password)); // BCRYPT
        // Ajusta estas 2 líneas a tu modelo real (enum/string):
        u.setRole(Role.valueOf(role.toUpperCase()));
        u.setEnabled(Status.valueOf(status.toUpperCase()));
        u.setEmail(username + "@example.com");

        return ResponseEntity.ok(repo.save(u));
    }
}