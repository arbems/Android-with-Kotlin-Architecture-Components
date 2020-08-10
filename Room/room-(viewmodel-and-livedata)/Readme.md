# Android con Kotlin - Room con ViewModel y LiveData

ViewModel funciona con Room y LiveData para reemplazar al cargador. El ViewModel garantiza que los datos sobrevivan al cambio de configuración del dispositivo. Room informa sobre tu LiveData cuando la base de datos cambia y LiveData, a su vez, actualiza la IU con los datos revisados.

A partir de Android P (API 28), se dieron de baja los cargadores. La opción recomendada de administrar la carga de datos mientras se manejan los ciclos de vida de Activity y Fragment es usar una combinación de ViewModels y LiveData.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.