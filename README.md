# Proyecto Microservicios

Este repositorio contiene tres microservicios desarrollados con Spring Boot y Docker:

- **ms-auth**: Microservicio de autenticación y gestión de tokens.
- **ms-user**: Microservicio de gestión de usuarios.
- **ms-publications**: Microservicio de gestión de publicaciones.

## Estructura del Repositorio

```
.
├── ms-auth
├── ms-user
├── ms-publications
└── README.md
```

Cada microservicio tiene su propio `Dockerfile` y configuración de despliegue independiente.

## Requisitos Previos

- Docker y Docker Compose instalados.
- Java 18 o superior.
- Maven 3.8.6 o superior.

## Construcción de Imágenes Docker

Ejecutar el siguiente comando en la raíz de cada microservicio para construir su imagen Docker:

```bash
# En la carpeta de cada microservicio
cd ms-auth   # O ms-user, ms-publications

# Construir la imagen Docker
./mvnw clean package -DskipTests

docker build -t nombre_imagen .
```

## Ejecución de Contenedores Docker

Para levantar los microservicios de manera individual:

```bash
# Levantar ms-auth
cd ms-auth

docker run -d -p 8081:8080 --name ms-auth nombre_imagen

# Levantar ms-user
cd ../ms-user

docker run -d -p 8082:8080 --name ms-user nombre_imagen

# Levantar ms-publications
cd ../ms-publications

docker run -d -p 8083:8080 --name ms-publications nombre_imagen
```

## Levantar Todo con Docker Compose

Si deseas levantar todos los microservicios al mismo tiempo, crea un archivo `docker-compose.yml` en la raíz del proyecto con la siguiente configuración:

Luego, ejecuta:

```bash
docker-compose up --build -d
```

## Acceso a los Microservicios

- `ms-auth`: [http://localhost:8081](http://localhost:8081)
- `ms-user`: [http://localhost:8082](http://localhost:8082)
- `ms-publications`: [http://localhost:8083](http://localhost:8083)

## Apagar los Microservicios

```bash
docker-compose down
```

## Notas

- Asegúrate de configurar correctamente la comunicación entre los microservicios si se van a comunicar entre ellos.
- Revisa los logs de cada contenedor con:

```bash
docker logs -f nombre_contenedor
```

