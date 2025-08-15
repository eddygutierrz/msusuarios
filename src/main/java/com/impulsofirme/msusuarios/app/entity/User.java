package com.impulsofirme.msusuarios.app.entity;

import java.util.List;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Auditable {
    //ID
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    // USER FIELDS
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String role;
    private String enabled;
    private String token;
    private String office;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
      name = "user_accessible_offices",
      joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "office", length = 64, nullable = false)
    private List<String> accessibleOffices = List.of();

    // PERSONAL FIELD
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}