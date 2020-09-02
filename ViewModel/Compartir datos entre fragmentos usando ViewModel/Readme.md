# Android con Kotlin - Compartir datos entre fragmentos usando ViewModel

*Este ejemplo muestra las siguientes características de [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)*:

* Muestra comunicación entre diferentes Fragmentos de una Actividad con ViewModel

# Documentación

Es muy común que dos o más fragmentos de una actividad necesiten comunicarse entre sí. Los dos fragmentos deben administrar una situación en la que el otro fragmento todavía no se creó o no está visible.

Para solucionar esta dificultad habitual, puedes usar objetos ViewModel. 

ViewModels se puede utilizar como capa de comunicación entre diferentes Fragmentos de una Actividad. Cada Fragmento puede adquirir ViewModel usando la misma clave a través de su Actividad. Esto permite la comunicación entre Fragmentos de forma desacoplada de modo que nunca necesiten hablar directamente con el otro Fragmento.

**Ventajas de este enfoque:**

* La Activity no necesita hacer nada ni saber sobre esta comunicación.
* Los fragmentos no necesitan saber acerca del otro, excepto por el contrato de ViewModel. Si uno de los fragmentos desaparece, el otro sigue funcionando de manera habitual.
* Cada fragmento tiene su propio ciclo de vida y no se ve afectado por el ciclo de vida del otro. Si un fragmento reemplaza al otro, la IU continúa funcionando sin problemas.

Ejemplo comunicación entre fragmentos y actividad usando ViewModel:

ViewModel:
```kotlin
class SharedViewModel : ViewModel() {
    private val _selected = MutableLiveData<Boolean>()
    val selected: LiveData<Boolean> get() = _selected

    fun select(selected: Boolean) {
       _selected.value = selected
    }
}
```   
Activity:
```kotlin
// Usa 'by viewModels()' Kotlin property delegate from the activity-ktx artifact
private val viewModel: SharedViewModel by viewModels()
```

Primer fragmento:
```kotlin
// Usa 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
private val viewModel: SharedViewModel by activityViewModels()
```

Segundo fragmento:
```kotlin
// Usa 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
private val viewModel: SharedViewModel by activityViewModels()
```



`Ten en cuenta que ambos fragmentos recuperan la actividad que los contiene. De esa manera, cuando cada fragmento obtiene el ViewModelProvider, reciben la misma instancia SharedViewModel, cuyo alcance está determinado por esta actividad.`

ViewModels también se puede usar como una capa de comunicación entre diferentes fragmentos de una actividad. Cada fragmento puede adquirir el ViewModel usando la misma clave a través de su actividad. Esto permite la comunicación entre los Fragmentos de forma desacoplada, de modo que nunca necesitan hablar directamente con el otro fragmento.




## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.