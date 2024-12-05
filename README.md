# Backend de Gestión de Inventario de Productos Perecederos

Adjunto al proyecto encontraras el Backup de mi base de datos MYSQL 

## Descripción General
Este backend está diseñado para gestionar un inventario de productos perecederos, permitiendo registrar productos, administrar entradas y salidas del inventario, y calcular el estado de los productos basándose en sus fechas de caducidad.

### Características principales:
- **CRUD de Productos**: Permite crear, leer, actualizar y eliminar productos.
- **Gestor de Inventario**: Administra entradas y salidas de inventario asociadas a productos.
- **Estados de Productos**: Calcula dinámicamente los estados ("Vigente", "Por vencer" o "Vencido") según las fechas de caducidad.

---

## Requisitos para Ejecutar

### Dependencias del Proyecto
- **Java**: Versión 17 o superior.
- **Maven**: Herramienta de gestión de dependencias.
- **Base de datos**: MySQL.
- **Spring Boot**: Framework principal del backend.

### Instalación
1. Clona el repositorio en tu máquina local:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd backend-inventario
   ```

2. Configura el archivo `application.properties` para conectarte a tu base de datos MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/inventario
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=create
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

3. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

4. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---

## Endpoints Principales

### Productos
- **GET /api/products**: Lista todos los productos.
- **POST /api/products**: Crea un nuevo producto.
- **GET /api/products/{id}**: Obtiene un producto por ID.
- **PUT /api/products/{id}**: Actualiza un producto.
- **DELETE /api/products/{id}**: Elimina un producto.

### Entradas de Inventario
- **POST /api/inventory/entries**: Agrega una entrada al inventario.

### Salidas de Inventario
- **POST /api/inventory/exits**: Realiza una salida del inventario basada en el sistema FIFO.

### Estado de Productos
- **GET /api/products/status**: Calcula el estado actual de todos los productos en base a sus fechas de caducidad.

---

## Decisiones Clave (Archivo DETAILS.md)

### Estructura del Sistema
1. **Arquitectura basada en capas**:
   - **Controladores**: Manejan las solicitudes HTTP.
   - **Servicios**: Contienen la lógica de negocio.
   - **Repositorios**: Se comunican con la base de datos.

2. **Uso de DTOs y Mappers**:
   - Se usaron **DTOs** para abstraer la información intercambiada con los clientes.
   - Los **Mappers** ayudan a convertir entidades a DTOs y viceversa.

3. **Validaciones**:
   - Validaciones a nivel de DTO usando `@Valid` y `javax.validation.constraints`.
   - Asegura que no se permitan cantidades negativas ni fechas de caducidad pasadas.

4. **Estado dinámico de los productos**:
   - Implementado para evitar almacenamiento innecesario en la base de datos.
   - Calculado en tiempo de ejecución basado en las fechas de caducidad.

### Sugerencias para Escalabilidad
1. **Mejorar la gestión de inventario**:
   - Implementar un sistema de notificaciones para alertar sobre productos "Por vencer".

2. **Base de datos distribuida**:
   - Escalar la base de datos para manejar mayores volúmenes de datos.

3. **API Gateway**:
   - Agregar un gateway para gestionar servicios adicionales en el futuro.

4. **Pruebas Automatizadas**:
   - Implementar pruebas unitarias y de integración para garantizar la calidad del código.

---
