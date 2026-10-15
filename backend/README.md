
# Apoyo Educativo

## Descripción del proyecto

Apoyo Educativo es una aplicación web desarrollada con el propósito de ofrecer un espacio digital de bienvenida y apoyo para los estudiantes.

Actualmente, el proyecto cuenta con un formulario que permite ingresar el nombre de un visitante y recibir un saludo personalizado.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- HTML5
- JavaScript
- Git y GitHub
- Visual Studio Code

## Organización del proyecto

La aplicación está organizada en tres capas:

- **Controller:** recibe las solicitudes realizadas desde el navegador.
- **Service:** contiene la lógica para generar los mensajes.
- **Model:** representa los datos de los visitantes.

Esta organización permite mantener el código ordenado y facilita futuras mejoras.

## Rutas disponibles

- `/api/saludo`: muestra un mensaje general de bienvenida.
- `/api/bienvenida?nombre=Roxana`: muestra un saludo personalizado.

## Cómo ejecutar el proyecto

1. Abrir la carpeta del proyecto en Visual Studio Code.
2. Abrir una terminal en la carpeta que contiene `pom.xml`.
3. Ejecutar el comando:

   `.\mvnw.cmd spring-boot:run`

4. Esperar a que Spring Boot inicie correctamente.
5. Abrir `http://localhost:8080` en el navegador.

## Estado actual

El proyecto cuenta con un controlador REST, una capa de servicio, un modelo de visitante y un formulario web que utiliza JavaScript y `fetch()` para mostrar mensajes sin recargar la página.

## Autora

Roxana Adelaida Pereira Delgado
