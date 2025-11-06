# Entidades de Referencia (Solo Lectura)

Estas entidades existen únicamente para **normalizar el dominio** y garantizar consistencia en los datos relacionados con usuarios, pagos y reservas.  
No se permite su modificación mediante la API; solo pueden ser consultadas.

---


## `PaymentMethodService`

**Finalidad:**  
Contiene los métodos de pago aceptados por el restaurante (CASH, CARD, TRANSFER).

**Operaciones disponibles:**
- **`GET /payment-method`**
    - **Salida**: `List<PaymentMethodDto>` contiene el `id` y `name` 
- **`GET /payment-method/{id}`**
    - **Salida**: `PaymentMethodDto` contiene el `id` y `name`

**Reglas de negocio:**
- CRUD deshabilitado.
- Los métodos se inicializan con datos predefinidos (seed o configuración inicial).
- No se pueden crear, actualizar ni eliminar desde la API.

---

## `BookingStatusService`

**Finalidad:**  
Define los posibles estados de una reserva (PENDING, PAID, CANCELLED).

**Operaciones disponibles:**
- **`GET /booking-status`**
    - **Salida**: `BookingStatusDto` contiene el `id` y `name` 
- **`GET /booking-status/{id}`**
    - **Salida**: `BookingStatusDto` contiene el `id` y `name`

**Reglas de negocio:**
- CRUD deshabilitado.
- Los estados se utilizan para controlar el flujo de una reserva.
- Solo el sistema puede modificar el estado de una reserva, nunca el usuario directamente.

---