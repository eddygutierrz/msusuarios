-- Secciones
insert into menus (id, section, icon, url, ord) values
    (1, 'Clientes',   'users',        'clients',      10),
    (2, 'Créditos',   'credit-card',  'credits',      20),
    (3, 'Cuenta',     'chart-bar',    'reports',      30),
    (4, 'Reportes',   'cog',          'settings',     40),
    (5, 'Comisiones', 'cog',          'settings',     50),
    (6, 'Usuarios',   'users',        'users',        60),
    (7, 'Especiales', 'cog',          'settings',     70);
on conflict (id) do nothing;

-- Pantallas
insert into screens (id, menu_id, name, path, ord) values
  -- Clientes
    (1, 1, 'Captura',               '/clients/new', 10),
    (2, 1, 'Modificar',             '/clients/update', 20),
    (3, 1, 'Listas',                '/clients/lists', 30)
  -- Créditos
    (4, 2, 'Captura',               '/credits/new', 10),
    (5, 2, 'Check List',            '/credits/check-list', 20),
    (6, 2, 'Desembolso',            '/credits/disbursement', 30),
    (7, 2, 'Administración',        '/credits/administration', 40),
    (8, 2, 'Monitor QR',            '/credits/monitor-qr', 60),
    (9, 2, 'Listas',                '/credits/lists', 50),
  -- Cuenta
    (10, 3, 'Estado de cuenta',     '/account/status', 10),
  -- Reportes
    (11, 4, 'Cartera Activa',       '/reports/credits/active', 10),
    (12, 4, 'Cartera Vencida',      '/reports/credits/overdue', 20),
    (13, 4, 'Cartera Liquidada',    '/reports/credits/settled', 30),
    (14, 4, 'Pagos próximos',       '/reports/next-payments', 40)
  -- Comisiones
    (15, 5, 'Comisiones',           '/commissions', 10),
  -- Usuarios
    (16, 6, 'Captura',              '/users/new', 10),
    (17, 6, 'Modificación',         '/users/update', 20),
  -- Especiales
    (25, 7, 'Garantia Liquida',     '/specials/new', 10),
    (26, 7, 'Configuración',        '/specials/configuration', 20)
    (27, 7, 'Traspasos Creditos',   '/specials/credits-transfer', 30);
on conflict (id) do nothing;

-- Permisos por rol (usa tu enum Role: EJECUTIVO, ADMINISTRADOR, GERENTE, ANALISTA, DIRECTOR, SISTEMAS)

-- SISTEMAS
insert into role_screens (role, screen_id) values
    ('SISTEMAS', 1),
    ('SISTEMAS', 2),
    ('SISTEMAS', 3),
    ('SISTEMAS', 4),
    ('SISTEMAS', 5),
    ('SISTEMAS', 6),
    ('SISTEMAS', 7),
    ('SISTEMAS', 8),
    ('SISTEMAS', 9),
    ('SISTEMAS', 10),
    ('SISTEMAS', 11),
    ('SISTEMAS', 12),
    ('SISTEMAS', 13),
    ('SISTEMAS', 14),
    ('SISTEMAS', 15),
    ('SISTEMAS', 16),
    ('SISTEMAS', 17),
    ('SISTEMAS', 25),
    ('SISTEMAS', 26),
    ('SISTEMAS', 27);
on conflict (id) do nothing;

-- DIRECTOR
insert into role_screens (role, screen_id) values
    ('DIRECTOR', 1),
    ('DIRECTOR', 2),
    ('DIRECTOR', 3),
    ('DIRECTOR', 4),
    ('DIRECTOR', 5),
    ('DIRECTOR', 6),
    ('DIRECTOR', 7),
    ('DIRECTOR', 8),
    ('DIRECTOR', 9),
    ('DIRECTOR', 10),
    ('DIRECTOR', 11),
    ('DIRECTOR', 12),
    ('DIRECTOR', 13),
    ('DIRECTOR', 14),
    ('DIRECTOR', 15),
    ('DIRECTOR', 16),
    ('DIRECTOR', 17),
    ('DIRECTOR', 25),
    ('DIRECTOR', 26),
    ('DIRECTOR', 27);
on conflict (id) do nothing;

-- GERENTE
insert into role_screens (role, screen_id) values
    -- Clientes
    ('GERENTE', 2),
    -- Creditos
    ('GERENTE', 5),
    ('GERENTE', 9),
    -- Cuenta
    ('GERENTE', 10),
    -- Reportes
    ('GERENTE', 11),
    ('GERENTE', 12),
    ('GERENTE', 13),
    ('GERENTE', 14);
on conflict (id) do nothing;

-- ANALISTA
insert into role_screens (role, screen_id) values
    -- Clientes
    ('ANALISTA', 1),
    ('ANALISTA', 2),
    ('ANALISTA', 3),
    -- Creditos
    ('ANALISTA', 5),
    ('ANALISTA', 6),
    ('ANALISTA', 7),
    ('ANALISTA', 8),
    ('ANALISTA', 9),
    -- Cuenta
    ('ANALISTA', 10),
    -- Reportes
    ('ANALISTA', 11),
    ('ANALISTA', 12),
    ('ANALISTA', 13),
    ('ANALISTA', 14),
    -- Usuarios
    ('ANALISTA', 16),
    ('ANALISTA', 17),
    -- Especiales
    ('ANALISTA', 25),
    ('ANALISTA', 27);
on conflict (id) do nothing;

-- ADMINISTRADOR 
insert into role_screens (role, screen_id) values
    -- Clientes
    ('ADMINISTRADOR', 1),
    ('ADMINISTRADOR', 2),
    ('ADMINISTRADOR', 3),
    -- Creditos
    ('ADMINISTRADOR', 4),
    ('ADMINISTRADOR', 9),
    -- Cuenta
    ('ADMINISTRADOR', 10),
    -- Reportes
    ('ADMINISTRADOR', 11),
    ('ADMINISTRADOR', 12),
    ('ADMINISTRADOR', 13),
    ('ADMINISTRADOR', 14);
on conflict (id) do nothing;

-- EJECUTIVO
insert role_screens (role, screen_id) values
    -- Clientes
    ('EJECUTIVO', 3),
    -- Creditos
    ('EJECUTIVO', 9),
    -- Cuenta
    ('EJECUTIVO', 10),
    -- Reportes
    ('EJECUTIVO', 11),
    ('EJECUTIVO', 12),
    ('EJECUTIVO', 13),
    ('EJECUTIVO', 14);
on conflict (id) do nothing;