# Android con Kotlin - Data Binding

*Este ejemplo muestra las siguientes características de [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)*:

* Configuración de Data Binding
* Diseño de vista, importaciones, variables e includes
* Clase de vinculación
* Manejo de eventos

# Documentación

## Configuración de Data Binding

* Añadir al archivo Gradle:

        android {
            ...
            // deprecated:
            dataBinding {
                enabled = true
            }
            // correct:
            buildFeatures {
                dataBinding true
            }
        }

* Para poder usar bibliotecas como Dagger o Data Binding en sus proyectos de Kotlin añade:

        apply plugin: 'kotlin-kapt'

## Diseño de vista, importaciones, variables e includes

### Diseño de vista
  
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewmodel"
            type="com.myapp.data.ViewModel" />
    </data>
    <ConstraintLayout... /> <!-- UI layout's root element -->
</layout>
```
\* Documentación Android: [**Diseños y expresiones vinculantes**](https://developer.android.com/topic/libraries/data-binding/expressions)

### Importaciones

Las importaciones facilitan la referencia de clases dentro de los archivos de diseño. **java.lang.*** se importa automáticamente.

Se pueden usar los tipos importados como referencias de tipo en variables y expresiones:

```xml
<data>
    <import type="com.myapp.data.ViewModel"/>
    <import type="java.util.List"/>
    <import type="com.myapp.data.User"/>

    <variable name="viewModel" type="ViewModel"/>
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
    <import type="com.myapp.MyStringUtils"/>
    <variable name="user" type="com.myapp.data.User"/>
</data>
<ConstraintLayout>
    <TextView android:text="@{MyStringUtils.capitalize(user.lastName)}" />
</ConstraintLayout>
```

Cuando hay conflictos de nombre de clase, una de las clases puede renombrarse con un alias:
```xml
    <data>
        <import type="android.view.View"/>
        <import type="com.myapp.real.estate.View" alias="Vista"/>
    </data>
```

### Variables

Data Binding genera métodos de acceso para cada variable declarada en el diseño. Las variables permiten describir una propiedad que puede usarse en expresiones de vinculación:

```xml
<data>
    <import type="android.graphics.drawable.Drawable"/>
    <variable name="user" type="com.myapp.data.User"/>
    <variable name="image" type="Drawable"/>
    <variable name="note" type="String"/>
</data>
```

### Includes

Los includes permiten reutilizar diseños complejos en tu app:
```xml
<data>
   <variable name="viewModel" type="com.myapp.data.ViewModel"/>
</data>
<LinearLayout>
   <include layout="@layout/address" bind:user="@{viewModel.address}"/>
   <include layout="@layout/contact" bind:user="@{viewModel.contact}"/>
</LinearLayout>
```

## Clases de vinculación

La biblioteca de vinculación de datos genera **clases de vinculación** que se usan para acceder a las variables y vistas del diseño.

Para cada archivo de diseño se genera una clase de vinculación, esta clase contiene variables de diseño, vistas de diseño y sabe cómo asignar valores para las expresiones de vinculación.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Binding class generated, for each design file a binding class is generated.
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(
        this, R.layout.activity_main)

    binding.viewModel = viewModel
}
```

Hay otras alternativas para aumentar el diseño, existe una versión alternativa del método inflate() que toma un objeto **ViewGroup** además del **LayoutInflater**:

```kotlin
val binding: MyLayoutBinding = MyLayoutBinding.inflate(getLayoutInflater(), viewGroup, false)
```

Si el diseño se aumentó con un mecanismo diferente, se puede vincular por separado:

```kotlin
val binding: MyLayoutBinding = MyLayoutBinding.bind(viewRoot)
```

A veces, el tipo de vinculación no se puede conocer de antemano. En esos casos, la vinculación se puede crear utilizando la **clase DataBindingUtil**:

```kotlin
val viewRoot = LayoutInflater.from(this).inflate(layoutId, parent, attachToParent)
val binding: ViewDataBinding? = DataBindingUtil.bind(viewRoot)
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

### Referencia de métodos

Los eventos pueden vincularse directamente a los métodos del controlador. Una ventaja importante en comparación con el atributo View de onClick es que la expresión se procesa en el momento de la compilación.

```xml
<layout>
   <data>
       <variable name="viewModel" type="com.myapp.data.ViewModel"/>
       <variable name="user" type="com.myapp.data.User"/>
   </data>
   <LinearLayout>
       <TextView android:text="@{user.firstName}"
           android:onClick="@{viewModel::onClickFriend}"/>
   </LinearLayout>
</layout>
```

```kotlin
fun onClickFriend(view: View) { /* ... */ }
```

### Objetos de escucha

Son expresiones lambda que se evalúan cuando ocurre el evento. La vinculación de datos siempre crea un objeto de escucha, que se establece en la vista. Cuando se lanza el evento, el objeto de escucha evalúa la expresión lambda.

```xml
<layout>
    <data>
        <variable name="viewModel" type="com.myapp.data.ViewModel"/>
    </data>
    <LinearLayout>
        <Button android:onClick="@{() -> viewModel.onSaveClick()}" />
    </LinearLayout>
</layout>
```

```kotlin
fun onSaveClick(){ /* ... */ }
```

La diferencia principal entre *referencia de métodos* y *objetos de escucha* es que la implementación real de objetos de escucha se crea cuando se vinculan los datos, no cuando se activa el evento.

En las referencias de métodos, los parámetros del método deben coincidir con los parámetros del objeto de escucha de eventos.
En las vinculaciones de objetos de escucha, solo el valor de resultado debe coincidir con el valor de resultado esperado del objeto de escucha.

Los objetos de escucha son similares a las referencias de métodos, pero permiten ejecutar expresiones de vinculación de datos arbitrarias.




## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.