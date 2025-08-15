package com.impulsofirme.msusuarios.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;
import com.impulsofirme.msusuarios.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener usuario por nombre de usuario
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío"); // Error 400
        }
        Optional<User> user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user.get());
    }

    // Obtener usuarios por rol
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    // Obtener usuarios por estado (habilitado o deshabilitado)
    @GetMapping("/enabled/{status}")
    public ResponseEntity<List<User>> getEnabledUsers(@PathVariable Status status) {
        return ResponseEntity.ok(userService.getEnabledUsers(status));
    }

    // Crear o actualizar un usuario
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        User existingUser = userService.getUserById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        updates.forEach((field, value) -> {
            switch (field) {
                case "username": existingUser.setUsername((String) value); break;
                case "firstname": existingUser.setFirstname((String) value); break;
                case "lastname": existingUser.setLastname((String) value); break;
                case "role": existingUser.setRole((String) value); break;
                case "office": existingUser.setOffice((String) value); break;
                case "accessibleOffices": 
                    // Asumiendo que es List<String>
                    existingUser.setAccessibleOffices((List<String>) value);
                    break;
                case "email": existingUser.setEmail((String) value); break;
                case "phone": existingUser.setPhone((String) value); break;
                case "enabled": existingUser.setEnabled((String) value); break;
            }
        });

        User updated = userService.saveUser(existingUser);
        return ResponseEntity.ok(updated);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // Desactivar o suspender usuario por ID
    @PutMapping("/disable/{id}")
    public ResponseEntity<User> disableUserById(@PathVariable Long id) {
        User disabledUser = userService.disableUserById(id);
        return ResponseEntity.ok(disabledUser);
    }

    // Cambiar contraseña de usuario por ID
    @PutMapping("/change-password/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwordData) {
        String newPassword = passwordData.get("newPassword");
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("La nueva contraseña no puede estar vacía"); // Error 400
        }
        User updatedUser = userService.changePassword(id, newPassword);
        return ResponseEntity.ok(updatedUser);
    }
}