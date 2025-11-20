# Task Management API (Gestor de Tareas) 

API RESTful robusta desarrollada con **Java** y **Spring Boot** para la gestión eficiente de tareas. Este proyecto implementa las mejores prácticas de desarrollo moderno, arquitectura limpia y documentación automática.

---

##  Stack Tecnológico
* **Lenguaje:** Java 17 (o 21, el que estés usando)
* **Framework:** Spring Boot 3.x
* **Base de Datos:** H2 Database (In-Memory)
* **Documentación:** OpenAPI / Swagger UI
* **Herramientas:** Maven, Git (Conventional Commits), Postman

## Características Técnicas Destacadas (Engineering Highlights)

Este no es solo un CRUD básico; es una implementación profesional enfocada en la calidad y mantenibilidad:

1.  **Arquitectura en Capas:** Separación estricta de responsabilidades (Controller, Service, Repository, DTO, Entity).
2.  **DTO Pattern:** Uso de Data Transfer Objects para desacoplar la API de la base de datos y proteger la integridad de los datos.
3.  **Validación Robusta:** Implementación de `Bean Validation` (`@NotNull`, `@Size`) para asegurar la calidad de los datos de entrada ("Garbage In, Garbage Out" protection).
4.  **Manejo Centralizado de Errores:** Uso de `@RestControllerAdvice` para transformar excepciones técnicas (stack traces) en respuestas JSON limpias y comprensibles (RFC 7807 compliant).
5.  **Documentación Viva:** Integración con Swagger UI para visualización y prueba interactiva de los endpoints.

##  Documentación de la API (Swagger)

Una vez iniciada la aplicación, puedes explorar y probar los endpoints directamente en:

 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

*(Aquí pones la captura de pantalla bonita de Swagger que me mandaste hace rato. Ponla en una carpeta /assets)*

## Cómo Ejecutar el Proyecto

### Prerrequisitos
* Java JDK 17+
* Maven

### Pasos
1.  Clonar el repositorio:
    ```bash
    git clone [https://github.com/Kujo-Joshua/gestor-tareas-api.git](https://github.com/Kujo-Joshua/gestor-tareas-api.git)
    ```
2.  Compilar y ejecutar:
    ```bash
    cd gestor-tareas-api
    mvn spring-boot:run
    ```

##  Testing
El proyecto incluye pruebas unitarias utilizando **JUnit 5** y **Mockito** para garantizar la lógica de negocio en la capa de servicio (aislamiento de dependencias).

---
**Autor:** Joshua Murguia  
*Software Engineering Student looking for Internship Opportunities.*
[LinkedIn](https://www.linkedin.com/in/joshua-abad-murguia-garcia-542a17288/)