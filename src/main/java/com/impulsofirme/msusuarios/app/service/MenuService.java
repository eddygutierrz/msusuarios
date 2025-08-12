package com.impulsofirme.msusuarios.app.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.impulsofirme.msusuarios.app.dto.MenuDTOs.MenuItemDTO;
import com.impulsofirme.msusuarios.app.dto.MenuDTOs.MenuSectionDTO;
import com.impulsofirme.msusuarios.app.entity.Screen;
import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.enums.Role;
import com.impulsofirme.msusuarios.app.repository.RoleScreenRepository;
import com.impulsofirme.msusuarios.app.repository.UserRepository;
import com.impulsofirme.msusuarios.app.repository.UserScreenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final UserRepository userRepository;
    private final RoleScreenRepository roleScreenRepository;
    private final Optional<UserScreenRepository> userScreenRepository;

    public List<MenuSectionDTO> getAuthorizedMenu(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return List.of();

        // 1) Por rol (tu User tiene un solo Role; si luego usas varios roles, aquí combinas)
        Role role = user.getRole();
        Set<Screen> screens = roleScreenRepository.findAllByRole(role)
            .stream().map(rs -> rs.getScreen())
            .collect(Collectors.toCollection(LinkedHashSet::new));

        // 2) Overrides por usuario (sumar)
        userScreenRepository.ifPresent(repo -> {
        repo.findAllByUserId(user.getId()).forEach(us -> screens.add(us.getScreen()));
        });

        // 3) Agrupar por sección y ordenar
        Map<Long, List<Screen>> bySection = screens.stream()
            .sorted(Comparator.comparing(s -> Optional.ofNullable(s.getOrder()).orElse(0)))
            .collect(Collectors.groupingBy(s -> s.getMenu().getId(), LinkedHashMap::new, Collectors.toList()));

        // 4) Construir DTOs ordenados por orden de sección
        return bySection.entrySet().stream()
            .sorted(Comparator.comparing(e ->
                Optional.ofNullable(e.getValue().get(0).getMenu().getOrder()).orElse(0)))
            .map(entry -> {
            var first = entry.getValue().get(0).getMenu();
            var children = entry.getValue().stream()
                .sorted(Comparator.comparing(s -> Optional.ofNullable(s.getOrder()).orElse(0)))
                .map(s -> new MenuItemDTO(s.getName(), s.getPath()))
                .toList();
            return new MenuSectionDTO(first.getSection(), first.getIcon(), first.getUrl(), children);
            }).toList();
  }

}