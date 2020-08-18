# Android con Kotlin - LiveData

LiveData es una clase que retiene datos observables. Otros componentes de tu app pueden supervisar cambios en objetos que usan este titular sin crear rutas de dependencia explícitas y rígidas entre ellos. El componente LiveData también respeta el estado del ciclo de vida de los componentes de tu app, como las actividades, los fragmentos y los servicios, e incluye lógica de limpieza para evitar las fugas de objetos y el consumo de memoria excesivo.

`Nota: Si ya estás usando una biblioteca como RxJava, puedes continuar usándola en lugar de LiveData. Sin embargo, cuando uses bibliotecas y enfoques como estos, asegúrate de administrar correctamente el ciclo de vida de tu app.`

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.