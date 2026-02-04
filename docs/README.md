# 📚 Screenmatch Literalura

**Screenmatch Literalura** es un proyecto desarrollado en **Java** con **Spring Boot**, que permite consultar libros de la API **Gutendex**, almacenar libros y autores en una base de datos PostgreSQL, y consultar información y estadísticas sobre ellos desde la consola.

## 📝 Características

El proyecto permite:

1. **Búsqueda de libros por título**
    - Consulta la API Gutendex y guarda los libros y sus autores en la base de datos.
    - Evita duplicados de autores.

2. **Listado de todos los libros guardados**
    - Muestra los libros con título, autor, idioma y cantidad de descargas.

3. **Listado de todos los autores**
    - Muestra los autores registrados junto con sus fechas de nacimiento y fallecimiento.

4. **Listado de autores vivos en un determinado año**
    - Permite consultar qué autores estaban vivos en un año específico.

5. **Estadísticas de libros por idioma**
    - Muestra la cantidad de libros en dos idiomas seleccionados por el usuario.

## 🛠 Tecnologías y Herramientas

- **Java 17+**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **PostgreSQL 16+**
- **Maven 4+**
- **Jackson** (para parsear JSON de la API)

## 🌐 API utilizada

- [Gutendex API](https://gutendex.com/)
    - Permite consultar información de más de 70.000 libros del **Project Gutenberg**.

## 🗂 Estructura del proyecto

├─ model → Clases de entidad y mapeo de datos de la API
│ ├─ Libro.java
│ ├─ Persona.java
│ ├─ DatosLibro.java
│ ├─ DatosPersona.java
│ └─ Gutendex.java
│
├─ repositorio → Interfaces JPA para libros y autores
│ ├─ LibroRepository.java
│ └─ PersonaRepository.java
│
├─ services → Clases para consumir API y convertir datos
│ ├─ ConsumirAPI.java
│ └─ ConvertirLosDatos.java
│
├─ principal → Clase con menú de interacción y lógica principal
│ └─ Principal.java
│
└─ ScreenmatchLiteraluraApplication.java → Clase principal con CommandLineRunner

## ⚙️ Configuración y ejecución

1. **Clonar el repositorio:**

```bash
git clone <url-del-repositorio>
cd screenmatch-literalura
```

2. **Configurar PostgreSQL**

Crear una base de datos.

Configurar application.properties o application.yml con tus credenciales:

spring.datasource.url=jdbc:postgresql://localhost:5432/screenmatch_literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. **Ejecutar la aplicación**

Desde IntelliJ IDEA: Run ScreenmatchLiteraluraApplication

4. **Interactuar con el menú**

Seleccionar opciones por número:

- 1 Buscar libro por título
- 2 Listar todos los libros
- 3 Listar todos los autores
- 4 Listar autores vivos en un año
- 5 Estadísticas por idioma

0 Salir
