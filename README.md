# Descripción

La aplicación se encarga del proceso de entrega/préstamos de libros de una biblioteca. 

Cuando el usuario solicita/entrega un libro al bibliotecario la aplicación debe leer una 
tarjeta que identifica al usuario a través de un dispositivo conectado vía TCP y 
registrar el evento en la base de datos. 

La aplicación recibe la petición de entrega o préstamo a través de un endpoint HTTP. 

Reglas:
1. Solo se pueden prestar hasta 5 libros a un usuario. 
1. El máximo tiempo que un usuario puede tener un libro prestado es de 1 mes. 
1. Si el usuario que lee el dispositivo (con la tarjeta) no existe en la base de datos pero está vigente 
su tarjeta, éste debe ser registrado en la base de datos.
1. No se puede prestar más de una vez un libro
1. Si el libro no existe, se registra

## Protocolo de comunicación al lector

### Solicutud de lectura
Comando: AX0

### Respuesta
Nombre del usuario|CURP|Fecha de expiración (YYYYMMDD)  

     

