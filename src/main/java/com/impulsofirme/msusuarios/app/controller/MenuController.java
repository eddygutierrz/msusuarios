package com.impulsofirme.msusuarios.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impulsofirme.msusuarios.app.dto.MenuDTOs.MenuSectionDTO;
import com.impulsofirme.msusuarios.app.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/{username}")
  public ResponseEntity<List<MenuSectionDTO>> getMenu(@PathVariable String username) {
    return ResponseEntity.ok(menuService.getAuthorizedMenu(username));
  }
}