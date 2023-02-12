# API Consumo Electricidad
API-REST encargada de devolver los consumos de electricidad por hora, día, semana y mes dada una fecha y un periodo. 

## Pasos para correr el proyecto.
### 1. Importar base de datos
Primero que todo, debes importar la [siguiente base de datos](https://github.com/Juan-Carlos-Estevez-Vargas/API-consumo-electricidad/blob/master/consumo_electricidad.sql) a phpmyadmin de MySQL.

### 2. Clonar el proyecto.
El segundo paso es clonar el repositorio e importarlo a algún IDE, en este caso se utilizó Spring Tool Suite; una vez importado se descargarán las dependencias maven.

### 3. Correr el proyecto.
Como tercer paso se necesita dar click derecho sobre el proyecto, run as Spring Boot App y esperar que el proyecto compile y se levante.

### 4. Probar los end points.
Para el proyecto se plantearon 4 end points, los cuales pueden ser probados mediante postman o por un curl:
* Postman: para ello, es necesario importar el siguiente [archivo](https://github.com/Juan-Carlos-Estevez-Vargas/API-consumo-electricidad/blob/master/postman_end_points/Consumo-Electricidad.postman_collection.json)
* CURL: 
  * Obtener Consumo periodo daily: curl -X GET "http://localhost:8080/consumption/daily" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"daily\"}"
  * Obtener Consumo periodo monthly: curl -X GET "http://localhost:8080/consumption/monthly" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"monthly\"}"
  * Obtener Consumo periodo weekly: curl -X GET "http://localhost:8080/consumption/weekly" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"weekly\"}"
  
## Explicación código paso a paso.

Puedes ver todo el proceso de construcción en mi canal de [YouTube](https://www.youtube.com/watch?v=RsF7nlLo5c0)
