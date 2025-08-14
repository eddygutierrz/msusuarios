package com.impulsofirme.msusuarios.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;
import com.impulsofirme.msusuarios.app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Buscar usuario por nombre de usuario
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Buscar usuarios por rol
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // Buscar usuarios habilitados
    public List<User> getEnabledUsers(Status enabled) {
        return userRepository.findByEnabled(enabled);
    }

    // Guardar o actualizar un usuario
    public User saveUser(User user) {
        //Si el usuario tiene un ID, se actualiza; si no, se crea uno nuevo.
        if (user.getId() == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptedPassword = passwordEncoder.encode(user.getUsername());
            user.setPassword(encryptedPassword); // Por defecto, al crear un usuario, se activa.
            user.setEnabled(Status.ACTIVE.toString()); // Establecer estado activo por defecto
        }
        return userRepository.save(user);
    }

    // Eliminar usuario por ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Verificar si un usuario existe por nombre de usuario
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Verificar si un usuario existe por correo electrónico
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}