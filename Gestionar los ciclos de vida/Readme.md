# Android con Kotlin - Gestionar los ciclos de vida con componentes de la arquitectura

Los componentes optimizados para ciclo de vida realizan acciones en respuesta a un cambio en el estado del ciclo de vida de otro componente, como actividades o fragmentos. 

Implementar las acciones de los componentes dependientes en los métodos del ciclo de vida de actividades y fragmentos genera una organización deficiente del código y la proliferación de errores.
Si usas componentes optimizados para ciclos de vida, puedes sacar el código de los componentes dependientes, que se encuentra en los métodos del ciclo de vida, y colocarlo en los propios componentes.

El paquete **androidx.lifecycle** ofrece interfaces y clases que te permiten compilar componentes optimizados para ciclos de vida.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.