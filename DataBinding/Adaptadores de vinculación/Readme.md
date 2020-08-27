# Android con Kotlin - Data Binding - Adaptadores de vinculación

*Código de ejemplo de adaptadores de vinculación en Android con Kotlin.*

Los adaptadores de vinculación se encargan de realizar las llamadas de framework apropiadas para establecer valores.

La librería de Data Binding te permite especificar el método al que se llama para establecer un valor, proporcionar tu propia lógica de vinculación y especificar el tipo de objeto mostrado mediante adaptadores.

Para cada expresión de diseño, hay un adaptador de vinculación que hace las llamadas de marco de trabajo requeridas para establecer las propiedades u objetos de escucha correspondientes.

Los adaptadores de vinculación más comunes, como los de la propiedad *android:text*, están disponibles para su uso en el paquete `android.databinding.adapters`
  
Lista de los [**adaptadores**](https://android.googlesource.com/platform/frameworks/data-binding/+/refs/heads/studio-master-dev/extensions/baseAdapters/src/main/java/androidx/databinding/adapters) de vinculación comunes.

## Establecer valores de atributo

Cada vez que un valor vinculado cambia, la clase de vinculación generada debe invocar un método set en la vista con la expresión de vinculación.

Puedes permitir que la biblioteca de vinculación de datos determine:
* automáticamente el método
* declare de manera explícita el método
* proporcione una lógica personalizada para seleccionar un método.

### Selección automática de métodos

Para un atributo llamado example, la biblioteca intenta encontrar automáticamente setExample(arg) que acepta tipos compatibles como el argumento.

La vinculación de datos funciona incluso si no existe un atributo con el nombre dado. Luego, puedes crear atributos para cualquier método set mediante vinculaciones de datos.

Por ejemplo, la clase de compatibilidad DrawerLayout no tiene ningún atributo, pero sí muchos métodos set. En el siguiente diseño, se utilizan de forma automática los métodos setScrimColor(int) y setDrawerListener(DrawerListener) como métodos set para los atributos app:scrimColor y app:drawerListener:

```xml
<android.support.v4.widget.DrawerLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:scrimColor="@{@color/scrim}"
        app:drawerListener="@{fragment.drawerListener}" />
```

### Especifica un nombre de método personalizado

Algunos atributos tienen métodos set que no coinciden por nombre. En esos casos, se puede asociar un atributo con el método set utilizando la anotación **BindingMethods**.

### Proporciona lógica personalizada



```xml
```

```kotlin
```

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.