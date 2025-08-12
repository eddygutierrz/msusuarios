package com.impulsofirme.msusuarios.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impulsofirme.msusuarios.app.entity.UserScreen;

public interface UserScreenRepository extends JpaRepository<UserScreen, Long> {
    List<UserScreen> findAllByUserId(Long userId);
}