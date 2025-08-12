package com.impulsofirme.msusuarios.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menus")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuSection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 60)
    private String section;        // Ej: "Usuarios", "Créditos"

    @Column(length = 40)
    private String icon;           // Ej: "users", "credit-card"

    @Column(length = 120)
    private String url;            // slug para marcar activo. Ej: "usuarios", "creditos"

    @Column(name = "ord", nullable = false)
    @Builder.Default
    private Integer order = 0;     // orden de la sección en el sidebar
}