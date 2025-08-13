package com.impulsofirme.msusuarios.app.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.impulsofirme.msusuarios.app.dto.MenuDTOs.MenuItemDTO;
import com.impulsofirme.msusuarios.app.dto.MenuDTOs.MenuSectionDTO;
import com.impulsofirme.msusuarios.app.entity.MenuSection;
import com.impulsofirme.msusuarios.app.entity.RoleScreen;
import com.impulsofirme.msusuarios.app.entity.Screen;
import com.impulsofirme.msusuarios.app.entity.User;
import com.impulsofirme.msusuarios.app.repository.RoleScreenRepository;
import com.impulsofirme.msusuarios.app.repository.UserRepository;
import com.impulsofirme.msusuarios.app.repository.UserScreenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final UserRepository userRepository;
    private final RoleScreenRepository roleScreenRepository;
    // Si aún no usarás overrides por usuario, comenta esta línea y su uso.
    private final UserScreenRepository userScreenRepository;

    public List<MenuSectionDTO> getAuthorizedMenu(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return List.of();

        // 1) Pantallas por rol (single‑role)
        var roleScreens = roleScreenRepository.findAllByRoleFetch(user.getRole());

        LinkedHashSet<Screen> screens = roleScreens.stream()
                .map(RoleScreen::getScreen)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 2) Overrides por usuario (opcional)
        if (userScreenRepository != null) {
            userScreenRepository.findAllByUserIdFetch(user.getId())
                    .forEach(us -> screens.add(us.getScreen()));
        }

        if (screens.isEmpty()) return List.of();

        // 3) Agrupar por sección, ordenando sección y pantallas (nulos al final)
        Map<MenuSection, List<Screen>> bySection = screens.stream()
                .sorted(Comparator
                        .comparing((Screen s) -> nullFirst(orderOfMenu(s)))
                        .thenComparing(s -> nullFirst(s.getOrder()))
                        .thenComparing(s -> nz(s.getName())))
                .collect(Collectors.groupingBy(
                        Screen::getMenu,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        // 4) A DTOs
        return bySection.entrySet().stream()
                .sorted(Comparator.comparing(e -> nullFirst(e.getKey() != null ? e.getKey().getOrder() : null)))
                .map(e -> {
                    MenuSection m = e.getKey();
                    var items = e.getValue().stream()
                            .sorted(Comparator
                                    .comparing((Screen s) -> nullFirst(s.getOrder()))
                                    .thenComparing(s -> nz(s.getName())))
                            .map(s -> new MenuItemDTO(s.getName(), s.getPath()))
                            .toList();
                    return new MenuSectionDTO(
                            m != null ? m.getSection() : "General",
                            m != null ? nz(m.getIcon()) : null,
                            m != null ? nz(m.getUrl()) : "",
                            items
                    );
                })
                .toList();
    }

    /* helpers */
    private static Integer orderOfMenu(Screen s) {
        return (s == null || s.getMenu() == null) ? null : s.getMenu().getOrder();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T nullFirst(T v){
        return v == null ? (T)(Integer)Integer.MIN_VALUE : v;
    }
    private static String nz(String s){ return s == null ? "" : s; }
}