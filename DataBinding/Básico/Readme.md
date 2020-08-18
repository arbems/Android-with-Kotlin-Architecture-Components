# Android con Kotlin - DataBinding básico

Código de ejemplo de una aplicación simple con DataBinding en Android con Kotlin.

## Configuración de DataBinding

* Añadir al archivo Gradle:

        android {
            ...
            dataBinding {
                enabled = true
            }
        }

* Para poder usar bibliotecas como Dagger o Data Binding en sus proyectos de Kotlin añade:

        apply plugin: 'kotlin-kapt'
  
 ## Layout
  
```xml
<layout>

    <data>
        <variable name="" type="" />
        <variable name="" type="" />
        ...
    </data>


    <ConstraintLayout>

        <TextView android:text="@{expression}" />

    </ConstraintLayout>

</layout>
```
* Documentación Android: [**Diseños y expresiones vinculantes**](https://developer.android.com/topic/libraries/data-binding/expressions)

### Importaciones, variables e inclusiones

Las importaciones facilitan la referencia de clases dentro de los archivos de diseño. **java.lang.*** se importa automáticamente.

Se pueden usar los tipos importados como referencias de tipo en variables y expresiones:

```xml
<data>
    <import type="com.example.User"/>
    <import type="java.util.List"/>
    <variable name="user" type="User"/>
    <variable name="userList" type="List&lt;User>"/>
</data>
```
Puedes usar los tipos importados para transmitir parte de una expresión:
```xml
<TextView android:text="@{((User)(user.connection)).lastName}" />
```
Los tipos importados también se pueden usar cuando se hace referencia a campos y métodos estáticos en expresiones:
```xml
<data>
    <import type="com.example.MyStringUtils"/>
    <variable name="user" type="com.example.User"/>
</data>
<TextView android:text="@{MyStringUtils.capitalize(user.lastName)}" />
```
Las variables permiten describir una propiedad que puede usarse en expresiones de vinculación:
```xml
<data>
    <import type="android.graphics.drawable.Drawable"/>
    <variable name="user" type="com.example.User"/>
    <variable name="image" type="Drawable"/>
    <variable name="note" type="String"/>
</data>
```
Cuando hay conflictos de nombre de clase, una de las clases puede renombrarse con un alias:
```xml
    <data>
        <import type="android.view.View"/>
        <import type="com.example.real.estate.View" alias="Vista"/>
    </data>
```
Los includes permiten reutilizar diseños complejos en tu app:
```xml
<data>
   <variable name="user" type="com.example.User"/>
</data>
<LinearLayout>
   <include layout="@layout/name" bind:user="@{user}"/>
   <include layout="@layout/contact" bind:user="@{user}"/>
</LinearLayout>
```

## Vincular datos

Para cada archivo de diseño se genera una clase de enlace, esta clase contiene variables de diseño, vistas de diseño y sabe cómo asignar valores para las expresiones de vinculación.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Clase de enlace generada
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(
        this, R.layout.activity_main)

    // Ahora puede acceder a variables de diseño y vistas de diseño.
    binding.user = User("username", "password")
}
```

Si usas elementos de vinculación de datos dentro de un adaptador **Fragment**, **ListView** o **RecyclerView**, es posible que prefieras usar los métodos inflate() de las clases de vinculación o la clase DataBindingUtil:

```kotlin
val listItemBinding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
// or
val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false)
```

## Manejo de eventos

La vinculación de datos permite escribir eventos de manejo de expresiones que se envían desde las vistas.
Puedes usar los siguientes mecanismos para manejar un evento:

* **Referencia de métodos**: Los eventos pueden vincularse directamente a los métodos del controlador. Una ventaja importante en comparación con el atributo View de onClick es que la expresión se procesa en el momento de la compilación.

```xml
<layout>
   <data>
       <variable name="handlers" type="com.example.MyHandlers"/>
       <variable name="user" type="com.example.User"/>
   </data>
   <LinearLayout>
       <TextView android:text="@{user.firstName}"
           android:onClick="@{handlers::onClickFriend}"/>
   </LinearLayout>
</layout>
```

```kotlin
class MyHandlers {
    fun onClickFriend(view: View) { /* ... */ }
}
```

* **Objetos de escucha**: Son expresiones lambda que se evalúan cuando ocurre el evento. La vinculación de datos siempre crea un objeto de escucha, que se establece en la vista. Cuando se lanza el evento, el objeto de escucha evalúa la expresión lambda.

```xml
<layout>
    <data>
        <variable name="task" type="com.android.example.Task" />
        <variable name="presenter" type="com.android.example.Presenter" />
    </data>
    <LinearLayout>
        <Button android:onClick="@{() -> presenter.onSaveClick(task)}" />
    </LinearLayout>
</layout>
```

```kotlin
class Presenter {
    fun onSaveClick(task: Task){ /* ... */ }
}
```

La diferencia principal entre *referencia de métodos* y *objetos de escucha* es que la implementación real de objetos de escucha se crea cuando se vinculan los datos, no cuando se activa el evento.

En las referencias de métodos, los parámetros del método deben coincidir con los parámetros del objeto de escucha de eventos.
En las vinculaciones de objetos de escucha, solo el valor de resultado debe coincidir con el valor de resultado esperado del objeto de escucha.

Los objetos de escucha son similares a las referencias de métodos, pero permiten ejecutar expresiones de vinculación de datos arbitrarias.














## Enlaces



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.