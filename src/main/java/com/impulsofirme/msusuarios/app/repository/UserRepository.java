package com.impulsofirme.msusuarios.app.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.enums.Office;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Buscar usuario por nombre de usuario
    Optional<User> findByUsername(String username);

    // Buscar usuario por oficina
    List<User> findByOffice(Office office);

    // Buscar usuarios por rol
    List<User> findByRole(Role role);

    // Buscar usuarios habilitados
    List<User> findByEnabled(Status enabled);

    // Buscar usuarios por correo electrónico
    Optional<User> findByEmail(String email);

    // Verificar si un usuario existe por nombre de usuario
    boolean existsByUsername(String username);

    // Verificar si un usuario existe por correo electrónico
    boolean existsByEmail(String email);
}