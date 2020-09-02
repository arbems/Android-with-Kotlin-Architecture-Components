# Android con Kotlin - LiveData (Biblioteca de Jetpack - Lifecycle)

*Proyecto con códigos de ejemplos de [LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData?hl=es-419) en Android con Kotlin.*

**LiveData** es una clase de contenedor de datos que se puede observar dentro de un ciclo de vida determinado.

[**Corrutinas con LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Corrutinas%20con%20ViewModel%20y%20LiveData)

[**Extender LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/LiveData/Extender%20LiveData)

[**Room con LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Room/Room%20con%20LiveData)

[**LiveData y DataBinding**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/DataBinding/Vincular%20vistas%20de%20dise%C3%B1o%20con%20componentes%20de%20arquitectura/DataBinding%20con%20ViewModel%20y%20LiveData)

[**Objetos LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/LiveData/Objetos%20LiveData)

[**Transformar LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/LiveData/Transformar%20LiveData)

# Documentación

**LiveData** es una clase contenedora de datos y tienen la capacidad de ser observable, está optimizada para ciclos de vida, lo que significa que respeta el ciclo de vida de otros componentes de las apps, como actividades, fragmentos o servicios. De esta forma, LiveData solo notificará a los observadores de componentes suscritos si estos están activos. También incluye lógica de limpieza para evitar las fugas de objetos y el consumo de memoria excesivo.

## Ventajas de usar LiveData

* #### Garantiza que la IU coincida con el estado de los datos
LiveData sigue el patrón del observador. LiveData notifica a los objetos Observer cuando cambia el estado del ciclo de vida. Puede consolidar tu código para actualizar la IU en estos objetos Observer. 
En lugar de actualizar la IU cada vez que cambian los datos de la app, tu observador puede hacerlo cada vez que se produzca un cambio.

* #### Ausencia de pérdidas de memoria
Los observadores están vinculados a objetos Lifecycle y borran lo que crean cuando se destruye el ciclo de vida asociado.

* #### Actividades detenidas para evitar las fallas
Si el ciclo de vida del observador está inactivo, como en el caso de una actividad de la pila de actividades, no recibe ningún evento de LiveData.

* #### No más manejo manual del ciclo de vida
Los componentes de IU solo observan los datos relevantes y no detienen ni reanudan la observación. LiveData se ocupa automáticamente de todo esto, ya que está al tanto de los cambios de estado del ciclo de vida relevantes mientras lleva a cabo la observación.

* #### Datos siempre actualizados
Si un ciclo de vida queda inactivo, recibe los datos más recientes una vez que vuelve a activarse. Por ejemplo, una actividad que estuvo en segundo plano recibe los datos más recientes inmediatamente después de volver a ejecutarse en primer plano.

* #### Cambios de configuración apropiados
Una actividad o un fragmento que se vuelve a crear debido a un cambio de configuración, como la rotación del dispositivo, recibe de inmediato los datos disponibles más recientes.

* #### Compartir recursos
Puedes extender un objeto LiveData con el patrón singleton para unir los servicios del sistema de modo que puedan compartirse en la app. El objeto LiveData se conecta al servicio del sistema una vez y, luego, cualquier observador que necesite el recurso puede simplemente mirar el objeto LiveData.


## LiveData VS Observable Object

A diferencia de los objetos que implementan [**Observable**](https://developer.android.com/reference/android/databinding/Observable), como los campos observables, los objetos [**LiveData**](https://developer.android.com/reference/androidx/lifecycle/LiveData?hl=es-419) conocen el ciclo de vida de los observadores suscritos a los cambios de datos. Tanto LiveData como ObservableFields observan los cambios, sin embargo las ventajas de uno sobre el otro que puedo señalar son:

* **Sin manipulación manual del ciclo de vida**. Los componentes de la interfaz de usuario solo observan datos relevantes y no se detienen ni reanudan la observación. LiveData gestiona automáticamente todo esto, ya que es consciente de los cambios de estado del ciclo de vida relevantes mientras observa.
* **Más funcionalidad con Transformations y MediatorLiveData**. El uso de LiveData le permitirá beneficiarse del poder de Transformations y también agregar múltiples fuentes a **MediatorLiveData**. Entonces, si tiene 5 vistas EditText en su diseño, no necesita observar las 5 de su Actividad o Fragmento. Puede observar solo un MediatorLiveData que le ahorrará algunas líneas de códigos y complejidad lógica.
* **Compartiendo recursos**. La creación de objetos personalizados que amplíen LiveData le permitirá conectarse al servicio del sistema una vez, luego cualquier observador que necesite el recurso puede simplemente observar el objeto.


## Attribution

This code was created by [**arbems**](https://github.com/arbems) in 2020.
