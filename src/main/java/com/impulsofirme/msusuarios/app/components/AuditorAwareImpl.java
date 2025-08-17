package com.impulsofirme.msusuarios.app.components;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        // Aquí puedes conectar con Spring Security si quieres
        return Optional.of("system"); // Puedes poner "admin", "backend", o luego integrar con JWT
    }
}