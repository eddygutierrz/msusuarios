package com.impulsofirme.msusuarios.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
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
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        // 1) Permitir explícitamente endpoints públicos
        if (path.startsWith("/api/users/auth/") ||
            path.startsWith("/actuator/health")) {
            chain.doFilter(req, res);
            return;
        }

        // 2) Exigir Authorization
        String bearer = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String token = bearer.substring(7).trim();

        try {
            Claims claims = jwt.parse(token).getBody();
            String username = claims.getSubject();
            // authorities: ["ROLE_ADMIN","ROLE_USER",...]
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("authorities");
            var authorities = roles == null ? List.<SimpleGrantedAuthority>of()
                    : roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(req, res);

        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}