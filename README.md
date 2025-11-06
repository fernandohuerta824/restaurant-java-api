# 🏨 API de Gestión de Reservas de un Restaurante

Esta es una **API REST** desarrollada con **Spring Boot** que implementa la **lógica central (core)** para la gestión de reservas en un restaurante.  
Actualmente el sistema se enfoca en la administración de **áreas**, **mesas**, **servicios extra** y **reservas**, sirviendo como la base para un futuro sistema más completo con autenticación, facturación y gestión de menús.

---

## ⚙️ Tecnologías utilizadas

- **Java 25**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database**
- **Validation API**
- **Swagger (OpenAPI)** para documentación interactiva

---

## 🧩 Arquitectura

El proyecto sigue una estructura basada en **Clean Architecture**, separando responsabilidades y facilitando la escalabilidad.

```
src/
├── config/   # Configuraciones de Spring y Swagger
├── controllers/      # Controladores REST
├── services/         # Lógica de negocio
├── constants/        # Constantes del proyecto
├── dto/              # Data Transfer Objects
├── exceptions/       # Manejo de excepciones personalizadas
├── helpers/         # Clases auxiliares
├── models/          # Entidades JPA
├── repositories/    # Repositorios JPA
└── response/           # ApiResponse y estructuras de respuesta estándar

```

El proyecto se apega al **clean architecture** de spring boot, separando claramente las capas de controladores, servicios, repositorios y modelos.

```
📤 Request
 ├─> 🧾 Request DTO
 │     └─ Validación de datos (anotaciones @Valid, @NotNull, etc.)
 │
 ├─> 🎯 Controller
 │     ├─ Recibe el DTO en el body (@RequestBody)
 │     ├─ Valida datos automáticamente (@Valid / @Validated)
 │     └─ Llama al servicio correspondiente
 │
 ├─> ⚙️ Servicio
 │     ├─ Aplica validaciones de negocio
 │     ├─ Lanza excepciones personalizadas si hay errores
 │     └─ Convierte el DTO a entidad antes de persistir
 │
 ├─> 🧱 Repositorio
 │     └─ Interactúa con la base de datos a través de JPA / Hibernate
 │
 └─> 🗄️ Datasource
       └─ H2 Database (o la base configurada en application.yml/properties)


📥 Response
 ├─> 🧱 Repositorio
 │     └─ Obtiene o persiste entidades usando JPA / Hibernate
 │
 ├─> ⚙️ Servicio
 │     ├─ Convierte entidades a DTOs
 │     ├─ Devuelve objetos: Dto, List<Dto>, Page<Dto>, etc.
 │     └─ Maneja errores y excepciones personalizadas
 │
 ├─> 🎯 Controller
 │     ├─ Recibe la respuesta del servicio
 │     ├─ Envuelve la respuesta en un ApiResponse<T> o ApiListResponse<T>
 │     └─ Devuelve respuesta normalizada con metadatos y estado HTTP adecuado
 │
 └─> 💡 Resultado final
       └─ JSON estructurado con datos, mensaje, código y timestamp

```


## 🧠 Modelos principales

| Entidad | Descripción |
|----------|-------------|
| **Area** | Define las áreas del restaurante (interior, terraza, bar, etc.) |
| **RestaurantTable** | Representa una mesa dentro de un área específica |
| **ExtraService** | Servicios adicionales ofrecidos (bebidas, decoración, etc.) |
| **Booking** | Reserva de una mesa, con posible asociación a servicios extra |
| **BookingExtraService** | Relación entre reservas y servicios extra |
| **BookingStatus** | Estado de la reserva (pendiente, confirmada, cancelada, etc.) |

---

## 🚀 Ejecución del proyecto

### Prerrequisitos
- Tener instalado **JDK 25** o superior

### Pasos
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/reservas-api.git
```

2. Ingresa al directorio:

   ```bash
   cd <nombre-del-proyecto>
   ```
3. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```
4. Accede a la documentación de la API:

   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Documentación de la API
La documentación interactiva de la API está disponible en Swagger UI una vez que la aplicación esté en ejecución:

```
http://localhost:8080/swagger-ui.html
```

Ademas puede ir [docs/entities.md](docs/entities.md) para ver detalles de las entidades y sus atributos.
Y ir a [docs/business-logic/](docs/business-logic/) para ver la lógica de negocio implementada.


## 🧭 Próximas mejoras

* 🔐 **Autenticación y autorización** con JWT y roles de usuario
* 🍽️ **Gestión del menú** (CRUD completo)
* 🔗 **Relación `bookings_menus`** para asociar platillos con reservas
* 💳 **Facturación y pagos** (modelar y implementar flujo de pagos)
* 📦 Migración a una base de datos relacional (PostgreSQL, MySQL)

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**, lo que permite su uso y modificación con fines educativos y profesionales.

---

## ✨ Autor

Desarrollado por **Fernando S. Huerta Márquez**
📍 Guadalajara, México
💻 Estudiante y desarrollador backend con enfoque en **Java, Spring Boot y arquitecturas limpias**
