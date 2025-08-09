package com.impulsofirme.msusuarios.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impulsofirme.msusuarios.app.dto.UserAuthDTO;
import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthSupportController {
    private final UserService userService;

    public AuthSupportController(UserService usuarioService) {
        this.userService = usuarioService;
    }

    @GetMapping("/auth/{username}")
    public ResponseEntity<UserAuthDTO> getUserForAuth(@PathVariable String username,
                                                      @RequestHeader(value="X-Internal-Auth", required=false) String internalHeader) {
        // Protección mínima entre servicios (ver 5.3)
        if (internalHeader == null || !internalHeader.equals(System.getenv("INTERNAL_AUTH_HEADER"))) {
            return ResponseEntity.status(403).build();
        }

        User u = userService.getUserByUsername(username).orElse(null);
        if (u == null) return ResponseEntity.notFound().build();

        UserAuthDTO dto = new UserAuthDTO(
            u.getId(), 
            u.getUsername(), 
            u.getPassword(), 
            u.getRole() != null ? u.getRole().name() : "USER",
            u.getEnabled() != null ? u.getEnabled().name() : "A",
            null
        );

        return ResponseEntity.ok(dto);
    }

}