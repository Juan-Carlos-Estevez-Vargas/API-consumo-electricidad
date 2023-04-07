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

  ```bash
  curl -X GET "http://localhost:8080/consumption/daily" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"daily\"}"

  curl -X GET "http://localhost:8080/consumption/monthly" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"monthly\"}"

  curl -X GET "http://localhost:8080/consumption/monthly" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2022-10-13\", \"period\": \"weekly\"}"

  ```
## Explicación código paso a paso.

Puedes ver todo el proceso de construcción en mi canal de [YouTube](https://www.youtube.com/watch?v=RsF7nlLo5c0)

### Si te ha gustado el proyecto invitame un café
<div align="left">
  <a href="https://paypal.me/JEstevezVargas" target="_blank" style="display: inline-block;">
    <img
      src="https://img.shields.io/badge/Donate-Buy%20Me%20A%20Coffee-orange.svg?style=flat-square&logo=buymeacoffee" 
      align="center"
     />
  </a>
</div>

## 🌐 Socials
[![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?logo=Facebook&logoColor=white)](https://facebook.com/juancarlos.estevezvargas.98) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/juankestevez) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://linkedin.com/in/juan-carlos-estevez-vargas) [![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?logo=YouTube&logoColor=white)](https://youtube.com/@apuntesdeuningeniero) 
