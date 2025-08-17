package com.impulsofirme.msusuarios.app.components;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
            .map(authentication -> authentication.getName()) // Obtén el nombre del usuario
            .or(() -> Optional.of("system")); // Valor predeterminado si no hay usuario autenticado
    }
}