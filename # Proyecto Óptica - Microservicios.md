Microservicios creados  
Lista con breve descripción:

service-cliente: gestiona datos de clientes

service-consulta: administra consultas

service-inventario: se agregan los lentes con sus y su tipo material

service-proveedor: maneja proveedores y datos

service-receta: gestiona recetas de pacientes

service-estadistica: gestiona las estadisiticas de lentes

service-medico: gestiona los medicos de consulta

service-envio: gestiona las venta de lentes

service-garantia: gestiona las garantias de ventas

service-venta: gestiona la venta de lentes

api-gateway: es la conexion con todo desde la vista del usuario


Aplicaciones requeridas

Java 21

Spring Boot 3.14x

Maven

MySQL


{
  "codigo": "LNT-001",
  "color": "Azul",
  "cantidad": 10,
  "tipoMaterial": {"id": 1, "nombre": "Plastico"},
  "idProveedor": 2
}

para ejecutar el proyecto se necesita: 
Maven 3.9 o superior
MySQL Server
Visual Studio Code
Github


El proyecto utiliza Swagger OpenAPI para documentar y probar los endpoints REST de cada microservicio de una forma mas visual.

Microservicio Proveedor
http://localhost:8081/swagger-ui/index.html

Microservicio Cliente
http://localhost:8082/swagger-ui/index.html

Microservicio Consulta
http://localhost:8083/swagger-ui/index.html
