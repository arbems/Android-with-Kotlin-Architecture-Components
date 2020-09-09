# Android con Kotlin - Corrutinas

*Este ejemplo muestra las siguientes características de [Corrutinas]()*:

* Agregar dependencias de KTX
* Iniciar corrutina en el alcance de un ViewModel
* Iniciar corrutina en el alcance de un Lifecycle
* Suspender corrutinas optimizadas para ciclos de vida

# Documentación

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
Se cancelan todas las corrutinas iniciadas en este alcance cuando se destruye el *Lifecycle*. Puedes acceder al **CoroutineScope** de *Lifecycle* mediante las propiedades `lifecycle.coroutineScope` o `lifecycleOwner.lifecycleScope`.

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