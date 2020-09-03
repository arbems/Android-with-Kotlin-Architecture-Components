# Android con Kotlin - View Binding

*Proyecto con códigos de ejemplos de [View Binding](https://developer.android.com/topic/libraries/view-binding) en Android con Kotlin.*

* Configuración de View Binding
* Uso en actividades y fragmentos
* Diferencias View Binding y findViewById
* Comparación con Data Binding

# Documentación

**View Binding** es una función que te permite escribir más fácilmente código que interactúa con las vistas.
Una vez que la vinculación de vista está habilitada en un módulo, genera una clase de vinculación para cada archivo de diseño XML presente en ese módulo. Una instancia de una clase de vinculación contiene referencias directas a todas las vistas que tienen un ID en el diseño correspondiente.

`Nota: En la mayoría de los casos, la vinculación de vistas reemplaza a findViewById`

En muchos casos, la vinculación de vistas puede proporcionar los mismos beneficios que Data Binding con una implementación más simple y un rendimiento mejor. Si usas Data Binding principalmente para reemplazar las llamadas de *findViewById()*, te recomendamos reemplazarla con View Binding.

### Configuración de View Binding

View Binding se habilita módulo por módulo. Para habilitar en un módulo, agrega el elemento viewBinding a su archivo build.gradle

    android {
        ...
        viewBinding {
            enabled = true
        }
    }
    
Si deseas que se ignore un archivo de diseño mientras se generan clases de vinculación, agrega el atributo **tools:viewBindingIgnore="true"**

    <LinearLayout tools:viewBindingIgnore="true" ...></LinearLayout>

### Uso en actividades y fragmentos

Si se habilita View Binding para un módulo, se genera una clase de vinculación para cada archivo de diseño XML que contiene el módulo.

Cada clase de vinculación contiene referencias a la vista raíz y a todas las vistas que tienen un **ID**.

```kotlin
val binding = ActivityMainBinding.inflate(layoutInflater)
```

Cada clase de vinculación también incluye un método getRoot(), que proporciona una referencia directa para la vista raíz del archivo de diseño correspondiente. 
En este ejemplo, el método getRoot() de la clase ActivityMainBinding muestra la vista raíz LinearLayout.
```kotlin
val view = binding.root
```

Pasa la vista raíz a setContentView() para que sea la vista activa en la pantalla.
```kotlin
setContentView(view)
```

**En fragmentos**:
```kotlin
private var _binding: ResultProfileBinding? = null
// This property is only valid between onCreateView and onDestroyView.
private val binding get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = ResultProfileBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

override fun onDestroyView() {
    super.onDestroyView()
    // Los fragmentos sobreviven a sus vistas. Asegúrate de borrar las referencias a la instancia de clase de vinculación que se encuentran en el método onDestroyView() del fragmento.
    _binding = null
}
```

Ahora para usar la clase de enlace en fragmentos:

```kotlin
binding.name.text = "name-text"
```


## Diferencias View Binding y findViewById

View Binding tiene ventajas importantes frente al uso de findViewById:

* **Seguridad nula**: Debido a que la vinculación de vista crea referencias directas a las vistas, no hay riesgo de una excepción de puntero nulo debido a un ID de vista no válido. Además, cuando una vista solo está presente en algunas configuraciones de un diseño, el campo que contiene su referencia en la clase de vinculación se marca con @Nullable.
* **Seguridad de tipos**: Los campos de cada clase de vinculación tienen tipos que coinciden con las vistas a las que hacen referencia en el archivo XML. Esto significa que no hay riesgo de una excepción de transmisión de clase.

## Comparación con Data Binding

View Binding y Data Binding generan clases de vinculación que puedes usar para hacer referencia a vistas directamente.

View Binding está diseñada para procesar casos de uso más simples y proporciona los siguientes beneficios por sobre Data Binding:

* **Compilación más rápida**: View Binding no requiere procesamiento de anotaciones, por lo que los tiempos de compilación son más rápidos.

* **Facilidad de uso**: View Binding no requiere archivos de diseño XML etiquetados especialmente, por lo que es más rápido adoptarlos en tus apps. Una vez que habilites View Binding en un módulo, se aplicará automáticamente a todos los diseños de ese módulo.


En cambio, View Binding tiene las siguientes limitaciones en comparación con Data Binding:

* **View Binding no admite variables ni expresiones de diseño**, por lo que no se puede usar para declarar contenido de IU dinámico directamente desde archivos de diseño XML.

* **View Binding no admite la vinculación de datos bidireccional**.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.