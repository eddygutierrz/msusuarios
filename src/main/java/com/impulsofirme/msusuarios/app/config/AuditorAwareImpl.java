package com.impulsofirme.msusuarios.app.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public Optional<String> getCurrentAuditor() {
        // Aquí puedes conectar con Spring Security si quieres
        return Optional.of("system"); // Puedes poner "admin", "backend", o luego integrar con JWT
    }
}