# Android con Kotlin - Corrutinas kotlin con componentes de la arquitectura

*Proyecto con códigos de ejemplo de [Corrutinas]() en Android con Kotlin.*

#### [Coroutines](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines)

#### [Coroutines con LiveData](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Usar%20corrutinas%20con%20LiveData)

# Documentación:

Las **corrutinas** de Kotlin proporcionan una API que te permite escribir código asíncrono. Puedes definir un **CoroutineScope**, lo que te ayuda a administrar cuándo deben ejecutarse las corrutinas. Cada operación asíncrona se ejecuta dentro de un alcance particular.

En Android, las corrutinas ayudan a administrar tareas de larga duración que, de lo contrario, podrían bloquear el hilo principal y hacer que una app dejara de responder.

Las corrutinas son la solución recomendada para la **programación asíncrona en Android**. Por las siguientes razones:

* **Ligereza**: Puedes ejecutar muchas corrutinas en un solo subproceso debido a la compatibilidad con la **suspensión**, que no bloquea el subproceso en el que se ejecuta la corrutina. Ahora, la suspensión ahorra más memoria que el bloqueo y admite muchas operaciones simultáneas.

* **Menos fugas de memoria**: Usa la *simultaneidad estructurada* para ejecutar operaciones dentro de un alcance.

* **Compatibilidad con cancelación incorporada**: Se propaga automáticamente la cancelación a través de la jerarquía de corrutinas en ejecución.

* **Integración con Jetpack**: Muchas bibliotecas de Jetpack incluyen extensiones que proporcionan compatibilidad total con corrutinas. Además, algunas bibliotecas proporcionan su propio alcance de corrutina, que puedes usar para la simultaneidad estructurada.

Desde el punto de vista del rendimiento, las coroutines permiten la ejecución de miles y hasta millones de hilos concurrentemente con un uso de recursos eficiente haciendo más robusta la aplicación al ser más difícil de alcanzar un error que indique falta de memoria.


![scheme coroutines kotlin](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0001.png)

# Coroutine Context

Un contexto es un *conjunto de elementos* que definen cómo se ejecutará la corrutina.

* El contexto de corrutina incluye un **dispatcher** de corrutina que determina qué hilo o hilos usa la correspondiente corrutina para su ejecución.
* Incluye un **Job** que es responsable del ciclo de vida, la cancelación y las relaciones entre padres e hijos de la corrutina.
* Incluye un **manejador de excepciones** que se asocia al contexto.
* Y **nombre de la corrutina** a la que se asocia el contexto. Establecer un nombre es útil para efectos de depuración.

Hay dos maneras de asignar un Context, en el alcance de la corrutina o en el constructor de la corrutina (launch, async, etc.)

Todos los constructores de corrutinas, como *launch* y *async*, aceptan un parámetro opcional de **CoroutineContext** que se puede utilizar para especificar explícitamente el [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) para la nueva corrutina y otros elementos de contexto como el [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) de la corrutina, el [CoroutineExceptionHandler](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-exception-handler/index.html) de la corrutina y el [CoroutineName](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-name/index.html).

Podemos usar el operador (**+**) para definir el *conjunto de elementos* para un contexto:

```kotlin
launch(Dispatchers.Default + job + handleException + CoroutineName("test")) { }
```

## interface CoroutineContext

Cada corrutina en Kotlin tiene un **contexto** que está representado por una instancia de la [interfaz CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/). 

El contexto actual de la corrutina está disponible a través de la propiedad `coroutineContext`:

```kotlin
println("My context is: $coroutineContext")
```

**CoroutineContext** es un *indexed set* de instancias de [Element](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-element/). Un conjunto indexado es una mezcla entre un set y un map. Cada *Element* de este conjunto tiene una [Key](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-key.html).

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0002.png" width="600" /><br>

**Keys** que nos sirven para obtener los cuatro *Element* de nuestro **CoroutineContext**:

* [**Job**](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html): Obtenemos el **Job** de la corrutina a la que se asocia el contexto.
* [**ContinuationInterceptor**](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-continuation-interceptor/): Obtenemos el **CoroutineDispatcher** de la corrutina a la que se asocia el contexto.
* [**CoroutineExceptionHandler**](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-exception-handler/index.html): Obtenemos el **manejador de excepciones** de la corrutina a la que se asocia el contexto.
* [**CoroutineName**](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-name/index.html): Obtenemos el **nombre de la corrutina** a la que se asocia el contexto. Establecer un nombre es útil para efectos de depuración.

El contexto de la corrutina es inmutable, pero puede agregar elementos a un contexto usando el operador `plus`.
Podemos combinar elementos de un contexto con los elementos de otro contexto gracias al operador `plus`, devolviendo un nuevo contexto que contiene los elementos combinados.

### [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)

**Job** es parte del contexto. Una corrutina en sí misma está representada por un *Job*. Es responsable del ciclo de vida, la cancelación y las relaciones entre padres e hijos de la corrutina.

Los **Jobs** se pueden organizar en jerarquías de padres e hijos donde la cancelación de un padre conduce a la cancelación inmediata de todos sus hijos de forma recursiva.<br>

Cuando se lanza una corrutina en el *CoroutineScope* de otra corrutina, hereda su contexto a través de *CoroutineScope.coroutineContext* y el trabajo de la nueva corrutina se convierte en un elemento secundario del trabajo de la corrutina principal. Cuando se cancela la corrutina principal, todos sus elementos secundarios también se cancelan de forma recursiva.

Sin embargo, cuando se utiliza [GlobalScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-global-scope/index.html) para iniciar una corrutina, no hay un padre para el trabajo de la nueva corrutina. Por lo tanto, no está vinculado al alcance desde el que se lanzó y funciona de forma independiente.

Una corrutina padre siempre espera la finalización de todos sus hijos. Un padre no tiene que rastrear explícitamente a todos los hijos que lanza, y no tiene que usar Job.join para esperarlos al final.

### [Dispatchers](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html)

El **Dispatcher** de corrutina determina qué hilo o hilos utiliza la correspondiente corrutina para su ejecución.
Puede limitar la ejecución de corrutinas a un hilo específico, enviarlo a un grupo de hilos o dejar que se ejecute *unconfined*.

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0003.png" witdh="600"/>

Object Dispatcher agrupa varias implementaciones de *CoroutineDispatcher*:

* **Dispatchers.Default**: *CoroutineDispatcher* por defecto que utilizan todos los constructores estándar como launch, async, etc. si no se especifica un dispatcher ni ningún otro *ContinuationInterceptor* en su contexto. Utiliza un grupo común de subprocesos en segundo plano compartidos. Ésta es una opción adecuada para corrutinas informáticas intensivas que consumen recursos de la CPU, como cálculos, algoritmos, etc.

* **Dispatchers.IO**: *CoroutineDispatcher* que está diseñado para descargar tareas de E/S de bloqueo a un grupo compartido de subprocesos. En general, todas las tareas que bloquearán el hilo mientras esperan la respuesta de otro sistema: peticiones al servidor, acceso a la base de datos, sitema de archivos, sensores etc.

* **Dispatchers.Main**: *CoroutineDispatcher* que se limita al subproceso principal que opera con objetos de IU. Por lo general, estos *Dispatchers* son de un solo subproceso.

* **Dispatchers.Unconfined**: *CoroutineDispatcher* que inicia una corrutina en el hilo del llamador, pero solo hasta el primer punto de suspensión. Después de la suspensión, reanuda la corrutina en el hilo que está totalmente determinada por la función de suspensión que se invocó. Es apropiado para corrutinas que no consumen tiempo de CPU ni actualizan ningún dato compartido (como la interfaz de usuario) confinado a un hilo específico. *Dispatchers.Unconfined* no debe usarse en código general.

Elegir el **Dispatcher** incorrecto puede reducir o anular la efectividad de la corrutina, a tener en cuenta para elegir *Dispatcher*:

* Si el código interactúa con los elementos de la interfaz de usuario, *Dispatchers.Main* es apropiado.
* Si el código es intensivo en CPU. Es decir, el código realiza cálculos (CPU), *Dispatchers.Default* es apropiado ya que está respaldado por un grupo de subprocesos con tantos subprocesos como núcleos de CPU.
* El código es intensivo en IO. Es decir, el código se comunica a través de la red / archivo (IO). *Dispatchers.IO* es apropiado.

### [CoroutineExceptionHandler](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-exception-handler/index.html)

### [CoroutineName](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-name/index.html)

Los identificadores asignados automáticamente son buenos cuando las corrutinas se registran con frecuencia y solo necesita correlacionar los registros que provienen de la misma corrutina. Sin embargo, cuando una corrutina está vinculada al procesamiento de una solicitud específica o al realizar alguna tarea específica en segundo plano, es mejor nombrarla explícitamente para fines de depuración. El elemento de contexto CoroutineName tiene el mismo propósito que el nombre del hilo. Se incluye en el nombre del hilo que está ejecutando esta corrutina cuando el modo de depuración está activado.




# Coroutine Scope

### [interface CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)

Hay una interfaz llamada **CoroutineScope** consta de una única propiedad de tipo *CoroutineContext*:

```kotlin
public interface CoroutineScope {
    public abstract val coroutineContext: kotlin.coroutines.CoroutineContext
}
```

Cada vez que se crea un nuevo *Coroutine Scope*, se crea un nuevo *Job* y se asocia con él. Cada corrutina creada con este alcance se convierte en el hijo de este *Job*. Así es como se crea una relación padre-hijo entre corrutinas.
Si alguna de las corrutinas arroja una excepción no controlada, su *Job* principal se cancela, lo que finalmente cancela todos sus elementos secundarios. Esto se llama **concurrencia estructurada**.




## Coroutine Builders

| Primer encabezado | Segundo encabezado |
| ------------- | ------------- |
| Contenido de la celda  | Contenido de la celda  |
| Contenido de la celda  | Contenido de la celda  |

Los constructores son los que inician las corrutinas, se definen como función de extensión de *CoroutineScope* y toma *CoroutineContext* como parámetro, por lo que en realidad toma dos contextos de corrutina (ya que un *CoroutineScope* consta de una única propiedad `coroutineContext`).
Estos contextos se fusionan de modo que los elementos del contexto parámetro tienen prioridad sobre los elementos del alcance.


# Funciones de suspensión


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.