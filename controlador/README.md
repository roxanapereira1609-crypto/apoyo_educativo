# Controlador

Esta carpeta contiene las acciones que coordinan la comunicación entre la Vista y el Modelo del proyecto Apoyo Educativo.

## Acciones principales

### Registrar beneficiario
Recibe los datos enviados desde el formulario de registro.

Consulta al Modelo Beneficiario para validar los datos y guardar la información.

Si los datos son correctos, decide mostrar la Vista con el listado de beneficiarios registrados. Si existe un error, muestra nuevamente el formulario con el mensaje correspondiente.

### Iniciar sesión
Recibe los datos ingresados por el usuario en la pantalla de inicio de sesión.

Solicita la validación de los datos de acceso.

Según el resultado, decide mostrar la pantalla correspondiente o informar que los datos ingresados no son válidos.
