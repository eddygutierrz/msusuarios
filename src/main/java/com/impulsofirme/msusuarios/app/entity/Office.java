package com.impulsofirme.msusuarios.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "offices")
@Data
public class Office {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
}