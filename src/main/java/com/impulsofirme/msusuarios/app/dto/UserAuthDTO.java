package com.impulsofirme.msusuarios.app.dto;

import com.impulsofirme.msusuarios.app.entity.Office;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthDTO {
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status enabled;
    private String token;
    private Office office;

    public UserAuthDTO(Long id, String username, String password, Role role, Status enabled, String token, Office office) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.token = token;
        this.office = office;
    }
}