# Android con Kotlin - Corrutinas

*Este ejemplo muestra las siguientes características de [Corrutinas]()*:

* Coroutine Context
* Coroutine Builder
* Coroutine Scope

# Documentación

Es posible crear corrutinas dentro de otra corrutina sin ninguna limitación. Por lo tanto, una corrutina puede tener muchas corrutinas “hijas”, y éstas a su vez pueden tener más corrutinas “hijas” y así infinitamente.

## Coroutine Context

## Coroutine Builder

Los constructores de corrutinas.

### runBlocking

Crea una corrutina y suspende el hilo que lo ejecuta hasta que la corrutina finalice. Este constructor no debe ser usado nunca, excepto para hacer pruebas unitarias de nuestras suspend functions o para usarlo en el método main para jugar con las corrutinas.

Debido a que **runBlocking** no es una función de extensión de la interface *CoroutineScope*, se puede usar en el interior de cualquier función.

```kotlin
fun main() {
    println("Start")

    runBlocking {
        println("Before delay.")
        delay(1000)
        println("After delay.")
    }

    println("End")
}
// print: Start, Before delay, After delay y End.
```

Al ejecutar el código anterior se puede observar que aparentemente todo se realizó secuencialmente. Lo que en realidad pasa al crear una corrutina con *runBlocking* es que el hilo que la crea esperará a que la corrutina finalice para continuar con la ejecución en la línea que está inmediatamente después. Es decir, no imprime la palabra *End* hasta que la corrutina creada con runBlocking acabe su ejecución.

### launch

Este constructor crea una corrutina devolviendo un objeto de tipo **Job**.

Debido a que este constructor es una función de extensión de la interface **CoroutineScope**, se puede llamar solamente desde adentro de una corrutina o dentro de una *suspend function*. 

Se utiliza para hacer tareas que no requieren la devolución de ningún valor.

```kotlin
fun main() {
    println("Start")

    GlobalScope.launch {
        println("Before delay.")
        delay(5000)
        println("After delay.")
    }

    println("End")
    Thread.sleep(6000)
}
// print: Start, End, Before delay y After delay.
```

### async

Este constructor crea una corrutina devolviendo un objeto de tipo `Deferred<T>` siendo T el tipo de dato esperado. 

Se puede llamar solamente desde adentro de una corrutina o dentro de una *suspend function*.

Se utiliza para hacer tareas que requieren la devolución de algún valor.

*Deferred* es un “envoltorio” que va a contener el dato requerido al finalizar la ejecución de la corrutina. Para acceder a este dato será necesario hacer una llamada a la función `await` que provee el *objeto Deferred*. A su vez, *Deferred* es un tipo especial de *Job*, por lo que se podrá manipular igual que el *Job* devuelto por el constructor *launch*.

Para ello nos valemos del ejemplo con el constructor *runBlocking* y creamos una nueva corrutina con el constructor **async** adentro de ésta.
La manera de obtener el valor con la llamada `await()`.

```kotlin
fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}
```

Aunque el constructor **async** no bloquea ni suspende la ejecución del hilo que crea la corrutina, al haber hecho la llamada a `await()` el hilo quedará suspendido en esa línea a la espera del valor de la variable

### produce

Este constructor crea una corrutina que se utiliza para la comunicación por medio de canales (Channels) con otras corrutinas.

Su implementación puede ser un poco compleja.



## Coroutine Scope 

Las corrutinas siempre se ejecutan en algún contexto representado por un valor del tipo **CoroutineContext**.

Cada vez que usamos un constructor de corrutinas en realidad estamos haciendo una llamada a una función que recibe como primer parámetro un objeto de tipo **CoroutineContext**.

Lo que hace el modificador **suspend** es restringir que esa función solo pueda ser llamada desde adentro del bloque de una *corrutina* o dentro de otra *suspend function*.

Los constructores `launch` y `async` son en realidad funciones de extensión de la interface **CoroutineScope**. 
Mientras que el constructor `runBlocking` no es una función de extensión de *CoroutineScope*. Por ésto es que las llamadas a los constructores *launch* y *async* solo son posibles dentro del Scope de una corrutina.

Cada vez que se crea una corrutina con cualquiera de los constructores, se crea un Scope para esa corrutina. Este Scope hereda el contexto de la corrutina que la contiene, a menos que especifiquemos explícitamente dicho contexto en forma de parámetro cuando se hace la llamada al constructor.











## Conceptos

### Bloquear o suspender hilo

Bloquear un hilo significa que el hilo se mantendrá fuera de uso mientras este encuentre algo que lo bloquee. 
Por el contrario, suspender un hilo significa que el hilo estará libre y listo para ser usado en la ejecución de otras tareas mientras se encuentra a la espera de la liberación de un recurso.

### Thread.sleep vs. delay

La función `sleep` bloquea el hilo. La función `delay`, por el contrario, sí utiliza el modificador **suspend**, por lo que una llamada a esta función suspende el hilo.



## Alcance o ámbitos de corrutinas optimizados para ciclos de vida

Los alcances se encuentran en las *extensiones de KTX* de cada componente de arquitectura correspondiente. Los componentes de la arquitectura definen los siguientes alcances integrados:

ViewModelScope:

`androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0-beta01`

LifecycleScope:

`androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha01`


### ViewModelScope

Se definen para un objeto **ViewModel**. Si se borra *ViewModel*, se cancela automáticamente cualquier corrutina iniciada en este alcance.

Las corrutinas son útiles para cuando tienes trabajos que se deben hacer solo si **ViewModel** está activo.

Puedes acceder al **CoroutineScope** de un *ViewModel* mediante la propiedad `viewModelScope` del *ViewModel*:

```kotlin
class MyViewModel: ViewModel() {
    init {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
        }
    }
}
```

### LifecycleScope

Se definen para un objeto **Lifecycle**.
Se cancelan todas las corrutinas iniciadas en este alcance cuando se destruye el *Lifecycle*. Puedes acceder al **CoroutineScope** de *Lifecycle* mediante las propiedades `lifecycle.corrutinaScope` o `lifecycleOwner.lifecycleScope`.

```kotlin
class MyFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val params = TextViewCompat.getTextMetricsParams(textView)
            val precomputedText = withContext(Dispatchers.Default) {
                PrecomputedTextCompat.create(longTextContent, params)
            }
            TextViewCompat.setPrecomputedText(textView, precomputedText)
        }
    }
}
```

## Suspender corrutinas optimizadas para ciclos de vida

Aunque CoroutineScope proporciona una forma adecuada de cancelar automáticamente operaciones de larga duración, es posible que haya otros casos en los que quieras suspender la ejecución de un bloque de código, a menos que el Lifecycle esté en un estado determinado.

**Lifecycle** proporciona métodos adicionales: `lifecycle.whenCreated`, `lifecycle.whenStarted` y `lifecycle.whenResumed`. Se suspenderá cualquier ejecución de corrutina dentro de estos bloques si el Lifecycle no está al menos en el estado mínimo deseado.

Bloque de código que se ejecuta solamente cuando el Lifecycle asociado está al menos en el estado **STARTED**:

```kotlin
class MyFragment: Fragment {
    init { // Notice that we can safely launch in the constructor of the Fragment.
        lifecycleScope.launch {
            whenStarted {
                // The block inside will run only when Lifecycle is at least STARTED.
                // It will start executing when fragment is started and
                // can call other suspend methods.
                loadingView.visibility = View.VISIBLE
                val canAccess = withContext(Dispatchers.IO) {
                    checkUserAccess()
                }

                // When checkUserAccess returns, the next line is automatically
                // suspended if the Lifecycle is not *at least* STARTED.
                // We could safely run fragment transactions because we know the
                // code won't run unless the lifecycle is at least STARTED.
                loadingView.visibility = View.GONE
                if (canAccess == false) {
                    findNavController().popBackStack()
                } else {
                    showContent()
                }
            }

            // This line runs only after the whenStarted block above has completed.

        }
    }
}
```

Si el Lifecycle se destruye mientras una corrutina está activa mediante uno de los métodos when, se cancelará automáticamente la corrutina.
En el siguiente ejemplo, el bloque finally se ejecuta una vez que el estado de Lifecycle es **DESTROYED**:

```kotlin
class MyFragment: Fragment {
    init {
        lifecycleScope.launchWhenStarted {
            try {
                // Call some suspend functions.
            } finally {
                // This line might execute after Lifecycle is DESTROYED.
                if (lifecycle.state >= STARTED) {
                    // Here, since we've checked, it is safe to run any
                    // Fragment transactions.
                }
            }
        }
    }
}
```

`Nota: Ten en cuenta que, aunque la actividad se reinicie, no ocurrirá lo mismo con la corrutina.`

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.