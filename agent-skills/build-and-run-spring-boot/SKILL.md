---
name: build-and-run-spring-boot
description: Habilidad para compilar, empaquetar, probar y ejecutar la aplicación Spring Boot de Sistema Balístico.
---

# Compilación y Ejecución de Sistema Balístico

Este manual define el flujo de trabajo para compilar, probar y levantar la aplicación de gestión de evidencias balísticas.

## Requisitos Previos

- **Java 21**: La aplicación está configurada para compilarse con la versión 21 de Java.
- **Maven Wrapper**: Se incluye `./mvnw` y `mvnw.cmd` en la raíz para evitar la necesidad de tener Maven instalado localmente.

## Comandos Principales

### 1. Compilar y Correr Pruebas Unitarias
Para compilar y validar que todas las pruebas unitarias y de integración pasen correctamente:

En Windows (PowerShell/CMD):
```powershell
.\mvnw.cmd clean test
```

En Linux/macOS:
```bash
./mvnw clean test
```

### 2. Levantar la Aplicación en Modo de Desarrollo
Para levantar el servidor localmente (por defecto se ejecuta en el puerto `8080` con H2 en memoria):

En Windows (PowerShell/CMD):
```powershell
.\mvnw.cmd spring-boot:run
```

En Linux/macOS:
```bash
./mvnw spring-boot:run
```

### 3. Empaquetar para Producción
Para generar el archivo ejecutable `.jar` bajo el directorio `target/`:

En Windows (PowerShell/CMD):
```powershell
.\mvnw.cmd clean package
```

El JAR empaquetado quedará disponible en:
`target/sistema_balistico-0.0.1-SNAPSHOT.jar`

## Solución de Problemas Comunes

- **Error de puerto en uso (8080)**: Si el puerto 8080 está ocupado, puedes arrancar la aplicación en otro puerto usando el parámetro de consola:
  ```powershell
  .\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
  ```
- **Error en base de datos H2 en uso**: Como H2 se inicializa en memoria (`jdbc:h2:mem:balisticadb`), cada ejecución limpia el estado anterior e inserta las semillas automáticas del archivo `data.sql`.
