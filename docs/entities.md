#  Entidades del Sistema

Este documento describe las **entidades del dominio** del sistema de gestión de reservas.  
Cada entidad representa un concepto real del restaurante y define su estructura, relaciones y propósito dentro del negocio.

--- 


## `BookingStatus`

**Descripción:**  
Representa el estado actual de una reserva.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único del estado. |
| `name` | String | Nombre del estado (ej: PENDING, PAID, CANCELLED). |

**Relaciones:**
- 1:N con `Booking`.

---

## `PaymentMethod`

**Descripción:**  
Define el método de pago asociado a una reserva.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único. |
| `name` | String | Nombre del método (CASH, CARD, TRANSFER). |

**Relaciones:**
- 1:N con `Booking`.

---


## `ExtraService`

**Descripción:**  
Representa servicios adicionales que pueden incluirse en una reserva (ejemplo: vino, decoración, música en vivo).

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único del servicio. |
| `name` | String | Nombre del servicio. |
| `description` | String | Descripcion del servicio |
| `price` | BigDecimal | Costo del servicio. |


**Relaciones:**
- N:M con `Booking`.

---

## `Area`

**Descripción:**  
Representa una zona física dentro del restaurante, como la terraza o el salón principal.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único. |
| `name` | String | Nombre del área. |
| `description` | String | Descripcion del servicio |
| `available` | BOolean | Indica si una area esta habilitada |

**Relaciones:**
- 1:N con `RestaurantTable` (una área contiene varias mesas).
- 1:N con `Area` (una área puede estar dentro de otra area).

---

## `RestaurantTable`

**Descripción:**
Representa una mesa dentro de un área específica del restaurante.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único de la mesa. |
| `name` | String | Nombre o número de la mesa. |
| `capacity` | Integer | Capacidad máxima de personas. |
| `description` | String | Descripcion de la mesa |
| `available` | Boolean | Indica si la mesa está disponible para reservas. |
| `area` | Area | Área a la que pertenece la mesa. |

**Relaciones:**
- N:1 con `Area`.
- N:M con `Booking`. Una mesa puede estar asociada a múltiples reservas.

---

## `Booking`

**Descripción:**
Representa una reserva realizada por un cliente.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único de la reserva. |
| `customerName` | String | Nombre del cliente que realiza la reserva. |
| `date` | LocalDate | Fecha de la reserva. |
| `startTime` | LocalTime | Hora de inicio de la reserva. |
| `endTime` | LocalTime | Hora de finalización de la reserva. |
| `status` | BookingStatus | Estado actual de la reserva. |
| `creadedAt` | LocalDate | Fecha de creación de la reserva. |
| `updatedAt` | LocalDate | Fecha de última actualización de la reserva. |

**Relaciones:**
- N:1 con `BookingStatus`.
- N:M con `RestaurantTable`.
- N:M con `ExtraService`.

---

## `BookingExtraService

**Descripción:**  
Entidad intermedia que representa la relación entre reservas y servicios adicionales.

**Campos principales:**
| Campo | Tipo | Descripción |
|--------|------|-------------|
| `id` | Long | Identificador único. |
| `booking` | Booking | Reserva asociada. |
| `extraService` | ExtraService | Servicio adicional asociado. |
| `quantity` | Integer | Cantidad del servicio adicional. |
| `price` | BigDecimal | Precio de ese servicio adicional en ese momento. |


---


📘 **Siguiente:**  
Ver la lógica de negocio detallada en [`business-logic/](business-logic/)  