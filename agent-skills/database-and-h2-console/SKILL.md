---
name: database-and-h2-console
description: Habilidad para interactuar con la base de datos H2 en memoria y gestionar datos del Sistema Balístico.
---

# Gestión de Base de Datos H2

Este manual describe el funcionamiento de la base de datos H2 integrada y la gestión del esquema de datos para evidencias balísticas.

## Configuración de la Base de Datos

La aplicación utiliza **H2 en memoria** como base de datos por defecto. La configuración principal se encuentra en [application.properties](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/src/main/resources/application.properties):

- **URL de Conexión**: `jdbc:h2:mem:balisticadb`
- **Usuario**: `sa`
- **Contraseña**: (vacía)
- **Consola Web**: Habilitada en la ruta `/h2-console`
- **Actualización de Esquema**: `spring.jpa.hibernate.ddl-auto=update`

## Acceso a la Consola Web de H2

Cuando la aplicación está levantada localmente en el puerto `8080`:

1. Accede a [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
2. Asegúrate de configurar los campos de conexión exactamente de esta forma:
   - **Driver Class**: `org.h2.Driver`
   - **JDBC URL**: `jdbc:h2:mem:balisticadb`
   - **User Name**: `sa`
   - **Password**: (Dejar en blanco)
3. Haz clic en **Test Connection** para verificar y luego en **Connect**.

## Carga de Semillas (data.sql)

La aplicación incluye un script automático de inicialización en [data.sql](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/src/main/resources/data.sql) que se ejecuta al levantar la aplicación debido a la propiedad `spring.jpa.defer-datasource-initialization=true`.

El script inserta calibres por defecto (como `9mm Parabellum`, `.45 ACP`, etc.) y registros iniciales de evidencias balísticas con diferentes configuraciones de percusión (`ANULAR`, `CENTRAL`, `ELECTRICA`, `LATERAL`) y sentidos de giro (`DEXTRORSUM`, `SINISTRORSUM`, `NONE`).

## Consultas Útiles para Depuración

Puedes ejecutar estas consultas SQL directamente desde la consola H2:

```sql
-- Listar todos los proyectiles/evidencias activas
SELECT * FROM bullet_entity WHERE is_delete = FALSE;

-- Contar evidencias agrupadas por calibre
SELECT c.name, COUNT(b.id_bullet) 
FROM caliber_entity c 
LEFT JOIN bullet_entity b ON c.id_caliber = b.id_caliber 
GROUP BY c.name;

-- Inspeccionar imágenes cargadas para cada evidencia
SELECT b.case_file, bi.path_image 
FROM bullet_entity b
INNER JOIN bullet_images_entity bi ON b.id_bullet = bi.id_bullet;
```
