# Android con Kotlin - Room con LiveData

*Este ejemplo muestra las siguientes características de [Room]()*:

* Implementación de Room con LiveData
* Integración con ViewModel

## Documentación

La biblioteca de persistencias **Room** admite consultas observables, que muestran objetos LiveData.

**Room** genera todo el código necesario para actualizar el objeto LiveData cuando se actualiza una base de datos.

El código generado ejecuta la consulta de manera asíncrona en un subproceso en segundo plano cuando es necesario. Este patrón es útil para mostrar los datos en una IU sincronizada con los datos almacenados en una base de datos.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.

ViewModel funciona con Room y LiveData para reemplazar al cargador. El ViewModel garantiza que los datos sobrevivan al cambio de configuración del dispositivo. Room informa sobre tu LiveData cuando la base de datos cambia y LiveData, a su vez, actualiza la IU con los datos revisados.

A partir de Android P (API 28), se dieron de baja los cargadores. La opción recomendada de administrar la carga de datos mientras se manejan los ciclos de vida de Activity y Fragment es usar una combinación de ViewModels y LiveData.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.