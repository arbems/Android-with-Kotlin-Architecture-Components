# Android con Kotlin - LiveData basic

Código de ejemplo de una aplicación simple con LiveData en Android con Kotlin.
                                                                                                  
LiveData es una clase contenedora de datos y tienen la capacidad de ser observable, está optimizada para ciclos de vida, lo que significa que respeta el ciclo de vida de otros componentes de las apps, como actividades, fragmentos o servicios. 
De esta forma, LiveData sólo notificará a los observadores de componentes suscritos si éstos están activos. Incluye lógica de limpieza para evitar las fugas de objetos y el consumo de memoria excesivo.

Lo que diferencia a LiveData de una clase observable regular es que esta optimizada para ciclos de vida. Esta optimización garantiza que LiveData solo actualice observadores de componentes de apps que tienen un estado de ciclo de vida activo.

LiveData admite transformaciones y muchos componentes de arquitectura, como Room y WorkManager.


Ventajas de usar LiveData:

* Garantiza que la IU coincida con el estado de los datos
LiveData sigue el patrón del observador. LiveData notifica a los objetos Observer cuando cambia el estado del ciclo de vida. Puede consolidar tu código para actualizar la IU en estos objetos Observer. 
En lugar de actualizar la IU cada vez que cambian los datos de la app, tu observador puede hacerlo cada vez que se produzca un cambio.

* Ausencia de pérdidas de memoria
Los observadores están vinculados a objetos Lifecycle y borran lo que crean cuando se destruye el ciclo de vida asociado.

* Actividades detenidas para evitar las fallas
Si el ciclo de vida del observador está inactivo, como en el caso de una actividad de la pila de actividades, no recibe ningún evento de LiveData.

* No más manejo manual del ciclo de vida
Los componentes de IU solo observan los datos relevantes y no detienen ni reanudan la observación. LiveData se ocupa automáticamente de todo esto, ya que está al tanto de los cambios de estado del ciclo de vida relevantes mientras lleva a cabo la observación.

* Datos siempre actualizados
Si un ciclo de vida queda inactivo, recibe los datos más recientes una vez que vuelve a activarse. Por ejemplo, una actividad que estuvo en segundo plano recibe los datos más recientes inmediatamente después de volver a ejecutarse en primer plano.

* Cambios de configuración apropiados
Una actividad o un fragmento que se vuelve a crear debido a un cambio de configuración, como la rotación del dispositivo, recibe de inmediato los datos disponibles más recientes.

* Compartir recursos
Puedes extender un objeto LiveData con el patrón singleton para unir los servicios del sistema de modo que puedan compartirse en la app. El objeto LiveData se conecta al servicio del sistema una vez y, luego, cualquier observador que necesite el recurso puede simplemente mirar el objeto LiveData.


Nota: Asegúrate de almacenar objetos LiveData que actualicen la IU en objetos ViewModel, en lugar de una actividad o un fragmento. Para evitar las actividades y fragmentos demasiado cargados y para desacoplar las instancias LiveData de instancias de fragmentos o actividades específicas, y permitir que los objetos LiveData sobrevivan a los cambios de configuración.
      
      
## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.