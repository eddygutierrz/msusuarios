package com.impulsofirme.msusuarios.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impulsofirme.msusuarios.app.entity.RoleScreen;
import com.impulsofirme.msusuarios.app.enums.Role;

public interface RoleScreenRepository extends JpaRepository<RoleScreen, Long> {
    List<RoleScreen> findAllByRole(Role role);
}