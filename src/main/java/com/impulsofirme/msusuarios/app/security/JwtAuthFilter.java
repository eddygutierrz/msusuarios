package com.impulsofirme.msusuarios.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.impulsofirme.msusuarios.app.components.JwtTokenProvider;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwt;

    public JwtAuthFilter(JwtTokenProvider jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain)
            throws ServletException, IOException {
        // 2) Si no viene Authorization -> 401
        String bearer = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = bearer.substring(7).trim();

        try {
            // Lanza si firma inválida o expirado
            Claims claims = jwt.parse(token).getBody();

            String username = claims.getSubject();
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("authorities");

            var authorities = (roles == null ? List.<SimpleGrantedAuthority>of()
                    : roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(req, res);

        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } finally {
            // ¡Importante!: NO limpiar el contexto aquí.
            // SecurityContextPersistenceFilter se encargará ciclo a ciclo.
        }
    }

    /**
     * Delega cuáles paths NO se filtran al SecurityConfig, pero si quisieras
     * excluir aquí, podrías usar shouldNotFilter:
     */
    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; // ⬅️ Ignora completamente preflight en este filtro
        }
        String p = request.getRequestURI();
        return p.startsWith("/actuator/health")
            || p.equals("/api/users/bootstrap")
            || p.startsWith("/api/users/auth/");
    }
}