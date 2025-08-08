package com.impulsofirme.msusuarios.app.entity;

import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status enabled;
    private String token;
    @ManyToOne
	@JoinColumn(name = "office_id")
    private Office office;

    // PERSONAL FIELD
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}