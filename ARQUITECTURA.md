# Arquitectura MVC — Apoyo Educativo

El proyecto Apoyo Educativo utiliza el patrón Modelo-Vista-Controlador (MVC) para organizar sus diferentes responsabilidades.

## Diagrama MVC

Vista (Formulario de registro de beneficiario)

Controlador (Registrar beneficiario)

Modelo (Beneficiario)

Controlador

Vista (Listado de beneficiarios o mensaje de error)

## Modelo

El Modelo contiene los datos y las reglas de negocio.

- Beneficiario
- Encargado

## Vista

La Vista contiene las pantallas con las que interactúa el usuario.

- Formulario de registro de beneficiario
- Listado de beneficiarios registrados
- Pantalla de inicio de sesión

## Controlador

El Controlador recibe las acciones del usuario, consulta al Modelo y decide qué Vista mostrar.

Ejemplo:

Formulario → Controlador → Modelo → Controlador → Vista
