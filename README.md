# zoo

Proyecto para actualizar amistades en un zoológico diariamente por Alejandro Lajusticia Delgado

## Setup

Este proyecto requiere:
  **gradle**
  **java 1.8**
  **lombok** https://projectlombok.org/setup/intellij https://projectlombok.org/setup/eclipse

Una vez que el proyecto compile, al bajar todas las dependencias de gradle y tener configurado correctamente lombok, se puede levantar el proyecto directamente desde el IDE y ocupara el puerto **8080**.

He generado el bootjar resultante en el directorio: **/bootjar** se puede ejecutar mediante **$java -jar ./bootjar/zoo-0.0.1-SNAPSHOT.jar 

## Decisiones tomadas

Cuando se procesa un nuevo doy, cada animal rompe su relación con otro animal y solo puede romper la relación con 1 animal como máximo. Para la casuística de una nueva amistad es exactamente lo mismo. Esto implica que los animales, al no tener relaciones  de amistad inicialmente, como máximo va a tener siempre una amistad después de ser procesado un nuevo día.

También tiene la implicación de que al haber un número impar de animales inicialmente, hay uno que siempre se queda sin ningún amigo, así es la vida.

## Diseño

Para la solución me he basado en un diseño en arquitectura hexagonal, por ello he separado la aplicación en 2 dominios **animal** y **food**, el dominio food es trivial y lo único que hace es almacenar las distintas comidas y en el caso de que le pidan una que no existe la crea y la guarda.

Dentro de cada dominio el código está separado en 3 partes:
* **infrastructure** - IO como almacenamiento de datos, en esta solución no he usado una base de datos si no que se almacena en memoria todas las entidades, entrada de comandos por consola, rest controllers, gestión de notificaciones y la implementación del selector de AnimalAwakening, aunque este último no es el lugar normal lo he visto conveniente para facilitar la realización de los test unitarios de la aplicación.
* **application** - Casos de uso de la aplicación y eventos de aplicación
* **domain** - Entidades de dominio, servicios de dominio y interfaces que implementara la capa de infrastructure

Al utilizar un arquitectura hexagonal los test unitarios no son sobre únicamente una clase sino que se realizan desde la capa de aplicación hacia abajo, es decir que se testea la capa de aplicación y la de dominio como si fueran una única unidad mientras que la capa de infraestructura se mockea.

Por otro lado los test de integración ejecuta un test con todo el contexto de spring cargado y se lanza una petición directamente a los endpoints a testear.

El comando **gradle :check** lanza los test unitarios y al finalizar los test funcionales, con lo que al realizar la tarea **:build** hay que pasar los 2 tipos de tests.

En esta solución una vez arrancada la aplicación se pueden introducir comandos de consola después de que aparezca la frase: **Console is ready to read commands** los comandos son:
* **list** - ver todos los animales y sus amistades
* **newDay** - procesar un nuevo día y calcular amistades
* **exit** - salir de la aplicación

Así mismo para facilitar el uso y realización de test unitarios la aplicación levanta 3 endpoints bajo el puerto **8080**, que realizan las mismas funcionalidades que los comandos, son los siguientes:
* **[GET] localhost:8080/animals** - ver todos los animales y sus amistades
* **[GET] localhost:8080/newday** - procesar un nuevo día y calcular amistades, como resultado mostrara todos los animales y sus amistades resultantes
* **[GET] localhost:8080/exit** - salir de la aplicación
