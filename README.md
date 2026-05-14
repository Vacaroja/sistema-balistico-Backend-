# Sistema balístico

API REST con **Spring Boot** para gestión de datos balísticos (entidades JPA, H2 en memoria por defecto).

## Requisitos

- [Java 21](https://adoptium.net/)
- Maven (o el wrapper incluido: `mvnw` / `mvnw.cmd`)

## Cómo ejecutar el proyecto

En la raíz del repositorio:

```bash
./mvnw spring-boot:run
```

En Windows (PowerShell o CMD):

```bash
.\mvnw.cmd spring-boot:run
```

Por defecto la aplicación escucha en el puerto **8080**.

## Documentación API (Swagger UI)

La documentación interactiva usa **springdoc-openapi** (OpenAPI 3 + Swagger UI).

1. Arranca la aplicación (paso anterior).
2. Abre el navegador en una de estas URLs (equivalentes; la primera suele redirigir a la UI):

   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

3. Desde ahí puedes ver los tags, endpoints y probar las peticiones con **Try it out**.

**Especificación OpenAPI en JSON** (útil para clientes o Postman):

- [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Si cambias el puerto (`server.port` en `application.properties`), sustituye `8080` por el valor configurado.

## Consola H2 (opcional)

Con la configuración por defecto, la consola web de H2 está en [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Usa la misma URL JDBC, usuario y contraseña que en `src/main/resources/application.properties`.

## Empaquetado

```bash
./mvnw clean package
```

El JAR queda en `target/sistema_balistico-0.0.1-SNAPSHOT.jar` (versión según `pom.xml`).
