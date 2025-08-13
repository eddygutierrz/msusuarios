package com.impulsofirme.msusuarios.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.impulsofirme.msusuarios.app.entity.UserScreen;

public interface UserScreenRepository extends JpaRepository<UserScreen, Long> {
    @Query("""
        select us from UserScreen us
        join fetch us.screen s
        join fetch s.menu m
        where us.userId = :userId
    """)
    List<UserScreen> findAllByUserIdFetch(@Param("userId") Long userId);
}