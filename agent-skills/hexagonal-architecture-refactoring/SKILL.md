---
name: hexagonal-architecture-refactoring
description: Reglas y directrices para mantener y extender el código del Sistema Balístico utilizando Arquitectura Hexagonal (Puertos y Adaptadores).
---

# Guía de Arquitectura Hexagonal (Puertos y Adaptadores)

Este proyecto organiza el código bajo `com.ccc.sistema_balistico` separando dominio, aplicación e infraestructura dentro de `core/`.

---

## Estructura de Paquetes (rama actual)

```
com.ccc.sistema_balistico
│
├── SistemaBalisticoApplication.java
│
└── core/
    ├── domain/                              <-- Reglas y conceptos de negocio
    │   ├── enums/                           <-- PercussionType, TwistDirection
    │   └── exceptions/
    │       ├── custom/                      <-- BulletNotFound, CaliberNotFound, etc.
    │       └── handler/                     <-- GlobalExceptionHandler, FileExceptionHandler
    │
    ├── application/                         <-- Casos de uso y contratos de servicio
    │   ├── dto/                             <-- BulletDTO, ImageDTO
    │   ├── services/                        <-- Interfaces (BulletService, BulletImagesService, FileStorageService)
    │   └── usecase/                         <-- Implementaciones (BulletImpl, BulletImageImpl, FileStorageImpl)
    │
    └── infrastructure/                      <-- Adaptadores de entrada y salida
        ├── in/
        │   └── rest/
        │       ├── controller/              <-- BulletController
        │       └── mapper/                  <-- BulletMapper (DTO <-> Entity)
        └── out/
            └── persistence/
                ├── entity/                  <-- BulletEntity, CaliberEntity, BulletImagesEntity
                └── jpa/                     <-- BulletRepository, CaliberRepository (Spring Data)
```

---

## Reglas de Dependencia

1. **Dominio sin frameworks**: `core.domain` no debe depender de JPA, Spring Web ni entidades de persistencia.
2. **Casos de uso en `usecase`**: Las clases `*Impl` implementan las interfaces de `application.services` y orquestan repositorios/adaptadores.
3. **DTOs en aplicación**: `BulletDTO` vive en `application.dto`; el mapper en `infrastructure.in.rest.mapper` convierte entre DTO y entidades JPA.
4. **Persistencia aislada**: Las entidades JPA están en `infrastructure.out.persistence.entity`; los repositorios Spring Data en `infrastructure.out.persistence.jpa`.

---

## Cómo agregar una nueva funcionalidad (Paso a Paso)

1. **Enums o excepciones** en `core.domain` si aplica.
2. **DTO** en `core.application.dto`.
3. **Interfaz de servicio** en `core.application.services`.
4. **Implementación** en `core.application.usecase` (`XxxImpl`).
5. **Entidad JPA + repositorio** en `core.infrastructure.out.persistence`.
6. **Controller + mapper** en `core.infrastructure.in.rest`.
7. **Prueba unitaria** en `src/test/java/.../core/application/usecase/` (ver sección siguiente).

---

## Pruebas unitarias

Ubicación: `src/test/java/com/ccc/sistema_balistico/core/application/usecase/`

Plantilla para un caso de uso (`BulletImpl`):

- Usar `@ExtendWith(MockitoExtension.class)`.
- Mockear dependencias inyectadas (`BulletRepository`, `CaliberRepository`, `BulletImagesService`).
- Instanciar el caso de uso con `@InjectMocks` sobre `BulletImpl`.
- No levantar contexto Spring (`@SpringBootTest` es solo para `SistemaBalisticoApplicationTests`).
- Para listas de archivos usar `anyList()` al stubear `saveImageList`.
- Excepciones de negocio: `core.domain.exceptions.custom.*` y `core.domain.exceptions.custom.caliber.*`.

Ejecutar solo el test unitario:

```bash
.\mvnw.cmd test -Dtest=BulletImplTest
```

Dependencia requerida en `pom.xml`: `spring-boot-starter-test` (scope `test`).
