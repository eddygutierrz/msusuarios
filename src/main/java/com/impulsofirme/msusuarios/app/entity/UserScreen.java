package com.impulsofirme.msusuarios.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_screens",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "screen_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserScreen {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "screen_id")
  private Screen screen;
}