# ⚙️ Lógica de Negocio

La lógica de negocio del sistema se encuentra organizada en **servicios especializados**, cada uno responsable de un aspecto concreto del dominio, como la gestión de áreas, mesas, reservas y servicios adicionales.  
Cada servicio encapsula las reglas que definen el funcionamiento interno del restaurante, garantizando que las operaciones respeten la coherencia de los datos y la integridad del modelo.

Estos servicios actúan como un puente entre el controlador (que recibe las solicitudes del cliente) y el repositorio (que se comunica con la base de datos).  
Este diseño permite mantener una **separación clara de responsabilidades**, donde cada capa cumple un propósito específico:  
el controlador gestiona la comunicación externa, el servicio implementa las reglas de negocio, y el repositorio maneja la persistencia.

El proyecto adopta principios de **Clean Architecture**, asegurando independencia entre las capas, alta mantenibilidad y facilidad de evolución a futuro.  
Esto también facilita realizar pruebas unitarias y cambiar tecnologías subyacentes sin afectar la lógica principal.

---

## 🧩 DTOs y Mapeo

El sistema emplea **DTOs (Data Transfer Objects)** para aislar la representación interna de los datos del dominio respecto a la interfaz expuesta al cliente.  
Esto evita exponer entidades directamente y permite controlar qué información viaja en cada dirección (entrada o salida).  

El mapeo entre DTOs y entidades se realiza mediante una herramienta de conversión automática, lo que permite mantener el código limpio y coherente, además de garantizar que las transformaciones sean consistentes en toda la aplicación.  

En este enfoque, los DTOs no solo transportan datos, sino que también representan la intención del cliente al interactuar con la API: crear, actualizar o consultar información específica del dominio.

---

## ✅ Validaciones en el DTO

La validación comienza desde el nivel del DTO, asegurando que los datos recibidos cumplan con las reglas básicas antes de entrar a la lógica de negocio.  
Esto incluye verificar la presencia de campos obligatorios, la coherencia de formatos y restricciones mínimas o máximas definidas según el contexto.  

Estas validaciones previenen errores tempranos y garantizan que el sistema solo procese solicitudes correctamente estructuradas, reduciendo la posibilidad de inconsistencias o fallos posteriores.

---

## 🧠 Validaciones en el Servicio

Una vez superadas las validaciones iniciales, la **capa de servicio** aplica las reglas de negocio más complejas, aquellas que dependen del estado actual del sistema o de relaciones entre entidades.  
Aquí se determina, por ejemplo, si una mesa está disponible, si el horario solicitado es válido o si una reserva entra en conflicto con otra existente.

Este nivel de validación representa el conocimiento real del dominio: define qué significa "válido" para el negocio, más allá de los simples requisitos de formato o presencia de datos.  
Cualquier violación a estas reglas genera una excepción de negocio que impide operaciones incoherentes.

---

## ⚠️ Excepciones Personalizadas

El sistema utiliza un conjunto de **excepciones personalizadas** para representar distintos tipos de errores: desde recursos no encontrados hasta violaciones de reglas de negocio o errores de validación.  
Cada excepción tiene un propósito claro y comunica de manera precisa la causa del problema, lo que permite ofrecer respuestas comprensibles y consistentes a los clientes de la API.

Estas excepciones se manejan de forma centralizada, garantizando que las respuestas de error mantengan un formato unificado, con mensajes claros, códigos adecuados y trazabilidad controlada.

---

## 🧾 ApiResponse

Todas las respuestas del sistema se normalizan mediante una estructura de respuesta común.  
Esta estructura envuelve los datos reales junto con metadatos como mensajes, códigos de estado y marcas de tiempo.  

Gracias a este diseño, las respuestas son coherentes en toda la aplicación, independientemente del tipo de operación realizada.  
Esto facilita el consumo de la API desde clientes externos, mejora la trazabilidad de errores y mantiene una comunicación predecible entre el backend y cualquier interfaz de usuario o sistema externo.

---

## 🧭 Enfoque General

El flujo general del sistema sigue una línea clara y ordenada:

- El cliente realiza una solicitud con datos validados estructuralmente (DTO).
- El controlador recibe la petición y delega la lógica al servicio correspondiente.
- El servicio ejecuta las reglas de negocio y, si corresponde, interactúa con los repositorios.
- Los repositorios se comunican con la base de datos a través del ORM, manteniendo la persistencia de forma transparente.
- La respuesta se construye, se normaliza y se envía al cliente en un formato uniforme.

Este enfoque combina claridad conceptual, robustez técnica y coherencia estructural, asegurando que el sistema sea fácil de mantener, escalar y extender en futuras iteraciones del proyecto.
