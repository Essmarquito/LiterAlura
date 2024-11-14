# LiterAlura

**LiterAlura** es una aplicación de consola para gestionar libros, autores e idiomas. Permite buscar libros por título, listar libros registrados, consultar autores por año y realizar otras operaciones relacionadas con el catálogo de libros. Utiliza Spring Boot como framework para facilitar la gestión del ciclo de vida de la aplicación y el acceso a la base de datos.

## Requisitos

Antes de ejecutar la aplicación, asegúrate de tener lo siguiente:

- **Java 11 o superior** instalado.
- **Maven** para gestionar las dependencias.
- **Base de datos** (H2 por defecto o una base de datos relacional compatible).

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/LiterAlura.git
   cd LiterAlura
Si no tienes Maven instalado, puedes instalarlo o usar el wrapper de Maven incluido en el proyecto.

Compila el proyecto con Maven:

bash
Copiar código
mvn clean install
Si prefieres, puedes cambiar la configuración de la base de datos en el archivo application.properties dentro de src/main/resources/ para conectar una base de datos real.

Ejecución
Para ejecutar la aplicación, utiliza el siguiente comando:

bash
Copiar código
mvn spring-boot:run
Esto iniciará la aplicación y te mostrará el menú en la consola.

Uso
Cuando la aplicación se ejecute, aparecerá un menú en la consola con las siguientes opciones:

markdown
Copiar código
===== Menú =====
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Buscar autores por año
5. Buscar libros por idioma
6. Salir
Selecciona una opción:
Opción 1: Buscar libro por título
Te pedirá que ingreses el título del libro. Luego, buscará libros que coincidan con el título y los mostrará en la consola.

Opción 2: Listar libros registrados
Muestra todos los libros registrados en la base de datos.

Opción 3: Listar autores registrados
Muestra todos los autores registrados en la base de datos.

Opción 4: Buscar autores por año
Te pedirá que ingreses un año y luego te mostrará los autores que publicaron libros ese año.

Opción 5: Buscar libros por idioma
Te pedirá que ingreses un idioma (por ejemplo, "en" para inglés) y luego mostrará los libros que estén registrados en ese idioma.

Opción 6: Salir
Cierra la aplicación.

Estructura del Proyecto
El proyecto está organizado de la siguiente manera:

bash
Copiar código
src
 ├── main
 │    ├── java
 │    │    ├── com
 │    │    │    ├── alura
 │    │    │    │    ├── LiterAlura
 │    │    │    │    │    ├── initializer       # Contiene la clase de inicialización principal.
 │    │    │    │    │    ├── model             # Modelos de datos como Libro, Autor, Lenguaje.
 │    │    │    │    │    ├── principal         # Contiene la clase principal para la lógica del menú.
 │    │    │    │    │    └── service           # Contiene la lógica de negocio y acceso a datos (Servicio de libros).
 │    └── resources
 │         └── application.properties            # Configuración de la base de datos y otros parámetros.
 └── test                                       # Aquí irían las pruebas unitarias (si se implementan).
Componentes principales
LibroService: Maneja la lógica de negocio relacionada con los libros, como la búsqueda y listado de libros y autores.
LibroRepository: Repositorio que se conecta a la base de datos para realizar operaciones CRUD sobre los libros.
Principal: Contiene la lógica para mostrar el menú y procesar las opciones del usuario.
LiteraluraApplication: Clase principal de la aplicación que arranca la aplicación Spring Boot.
