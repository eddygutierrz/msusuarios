package com.impulsofirme.msusuarios.app.dto;

import java.util.List;

public class MenuDTOs {
    public record MenuItemDTO(String name, String path) {}
    public record MenuSectionDTO(String section, String icon, String url, List<MenuItemDTO> children) {}
}