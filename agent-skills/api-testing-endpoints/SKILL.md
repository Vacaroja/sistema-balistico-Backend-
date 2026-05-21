---
name: api-testing-endpoints
description: Habilidad para interactuar, testear y documentar las APIs del Sistema Balístico utilizando Swagger UI, OpenAPI y peticiones HTTP Multipart.
---

# Pruebas e Interacción con la API REST

Este manual detalla los endpoints expuestos, la personalización de Swagger, y los flujos de pruebas y control de errores.

## Documentación Interactiva (Swagger/OpenAPI)

La aplicación utiliza la dependencia `springdoc-openapi-starter-webmvc-ui` versión `2.8.5` para la generación automática de la documentación de la API.

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **JSON de Especificación OpenAPI**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Personalizaciones de Swagger en el Código

El controlador `BulletController` está altamente enriquecido con anotaciones OpenAPI para garantizar una interfaz de documentación interactiva comprensible y profesional:
- **`@Tag`**: Agrupa los endpoints bajo el nombre `"Registro Balístico"` con una descripción de su funcionalidad.
- **`@Operation`**: Describe de forma clara lo que hace cada endpoint (ej: `"Obtener evidencias paginadas"`, `"Registrar nueva evidencia"`).
- **`@ApiResponses`**: Mapea explícitamente los códigos de respuesta del servidor (200, 201, 400, 404, 413, 415) y sus respectivos significados para el cliente.
- **`@Parameter`**: Proporciona ejemplos prácticos (ej: `example = "1"`) y aclaraciones sobre los parámetros pasados por URL o Query.

---

## Flujos de Pruebas de Endpoints

### 1. Obtener Evidencias Paginadas (GET)
Retorna una página de evidencias balísticas activas (`is_delete = false`).

- **URL**: `http://localhost:8080/api/v1/bullet`
- **Parámetros opcionales (Paginación)**: `page`, `size`, `sort`.
- **Ejemplo curl**:
  ```bash
  curl -X GET "http://localhost:8080/api/v1/bullet?page=0&size=5&sort=createdAt,desc"
  ```

### 2. Registrar Nueva Evidencia con Imagen (POST Multipart)
Crea una evidencia balística asociándola a un calibre existente e imágenes físicas (.jpg, .jpeg, .png).

- **URL**: `http://localhost:8080/api/v1/bullet`
- **Cabecera**: `Content-Type: multipart/form-data`
- **Campos del Formulario**:
  - `caseFile` (Texto - Obligatorio): Identificador del expediente (ej: `EXP-2026-099`).
  - `landsAndGrooves` (Número - Obligatorio): Número de campos y macizos (ej: `6`).
  - `percussionType` (Enum - Obligatorio): Tipo de percusión (`ANULAR`, `CENTRAL`, `ELECTRICA`, `LATERAL`).
  - `twistDirection` (Enum - Obligatorio): Sentido de giro (`DEXTRORSUM`, `SINISTRORSUM`, `NONE`).
  - `caliber` (Número - Obligatorio): ID del Calibre (ej: `1`).
  - `manufacturer` (Texto): Fabricante del proyectil.
  - `file` (Archivo - Obligatorio): Uno o varios archivos de imagen.
- **Ejemplo curl**:
  ```bash
  curl -X POST "http://localhost:8080/api/v1/bullet" \
    -F "caseFile=EXP-2026-003" \
    -F "landsAndGrooves=6" \
    -F "percussionType=CENTRAL" \
    -F "twistDirection=DEXTRORSUM" \
    -F "caliber=1" \
    -F "manufacturer=Winchester" \
    -F "file=@/ruta/a/la/imagen.png"
  ```

### 3. Visualizar una Imagen Guardada (GET)
Retorna el archivo binario de la imagen cargada para ser mostrado directamente en el navegador.

- **URL**: `http://localhost:8080/api/v1/bullet/images/{fileName}`
- **Ejemplo**: `http://localhost:8080/api/v1/bullet/images/f38d38e2-04e4-4d89-9a1d-72e70e28f321.png`

---

## Validación de Errores y Excepciones Custom

La aplicación cuenta con manejadores globales (`GlobalExceptionHandler` y `FileExceptionHandler`) que estructuran las respuestas fallidas en un formato JSON estandarizado (`ApiErrorResponse`):

```json
{
  "timestamp": "2026-05-20T18:24:14",
  "status": 400,
  "error": "Validation Failed",
  "message": "Validation failed for the provided data",
  "path": "uri=/api/v1/bullet",
  "validationErrors": {
    "caseFile": "Expedient Required"
  }
}
```

### Respuestas de Error Comunes a Probar:
- **Código 400 (Bad Request)**: Datos inválidos o faltantes. Por ejemplo, al intentar crear una evidencia con campos nulos o con un ID menor a 1.
- **Código 404 (Not Found)**: Proyectil o Calibre inexistente en base de datos.
- **Código 410 (Gone)**: Cuando se intenta consultar un proyectil o calibre que ha sido borrado de manera lógica (`is_delete = true`). Retorna `Bullet Is Deleted` o `Caliber Is Deleted`.
- **Código 413 (Payload Too Large)**: Al subir una imagen que supera el límite configurado de **5 MB**.
- **Código 415 (Unsupported Media Type)**: Al subir un archivo con extensión no válida (cualquiera que no sea `.jpg`, `.jpeg` o `.png`).
