package com.impulsofirme.msusuarios.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "screens")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Screen {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuSection menu;

    @Column(nullable = false, length = 80)
    private String name;    // Ej: "Lista", "Crear"

    @Column(nullable = false, length = 160)
    private String path;    // Ej: "/users", "/users/new"

    @Column(name = "ord", nullable = false)
    @Builder.Default
    private Integer order = 0; // orden dentro de la sección
}