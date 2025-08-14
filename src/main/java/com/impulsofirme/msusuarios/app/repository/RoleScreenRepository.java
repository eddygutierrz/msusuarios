package com.impulsofirme.msusuarios.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.impulsofirme.msusuarios.app.entity.RoleScreen;

public interface RoleScreenRepository extends JpaRepository<RoleScreen, Long> {
    @Query("""
        select rs from RoleScreen rs
        join fetch rs.screen s
        join fetch s.menu m
        where rs.role = :role
    """)
    List<RoleScreen> findAllByRoleFetch(@Param("role") String role);
}