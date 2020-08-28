# Android con Kotlin - Data Binding - Adaptadores de vinculación (Binding Adapters)

*Código de ejemplo de adaptadores de vinculación con Data Binding en Android con Kotlin.*

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
* o proporcione una lógica personalizada para seleccionar un método.

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

```kotlin
@BindingMethods(value = [
    BindingMethod(
        type = android.widget.ImageView::class,
        attribute = "android:tint",
        method = "setImageTintList")])
```

Se utiliza dentro de una anotación BindingMethods para describir un cambio de nombre de un atributo al establecedor utilizado para establecer ese atributo. De forma predeterminada, un atributo *attr* se asociará con el setter *setAttr*.

`La mayoría de las veces no es necesario cambiar el nombre de los métodos set en las clases de marco de trabajo de Android. Los atributos ya se implementaron utilizando la convención de nombres para buscar automáticamente métodos coincidentes.`

### Proporciona lógica personalizada

Algunos atributos requieren una lógica de vinculación personalizada. Por ejemplo, no hay métodos set asociados para el atributo android:paddingLeft.
En su lugar, se proporciona el método setPadding(left, top, right, bottom).

Un método de adaptador de vinculación estático con la anotación BindingAdapter permite personalizar el nombre de un método set para un atributo.

```kotlin
@BindingAdapter("android:paddingLeft")
    fun setPaddingLeft(view: View, padding: Int) {
        view.setPadding(padding,
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom())
    }
```

Los tipos de parámetros son importantes. El primer parámetro determina el tipo de vista asociada con el atributo. El segundo parámetro determina el tipo aceptado en la expresión de vinculación para el atributo dado.

Se puede llamar a un cargador personalizado desde un subproceso de trabajo para cargar una imagen:

```kotlin
@BindingAdapter("imageUrl", "error")
    fun loadImage(view: ImageView, url: String, error: Drawable) {
        Picasso.get().load(url).error(error).into(view)
    }
```

```xml
<ImageView app:imageUrl="@{venue.imageUrl}" app:error="@{@drawable/venueError}" />
```

Si quieres que se llame al adaptador cuando se establece cualquiera de los atributos, puedes configurar la marca opcional requireAll del adaptador en false:

```kotlin
@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    if (url == null) {
        imageView.setImageDrawable(placeholder);
    } else {
        MyImageLoader.loadInto(imageView, url, placeholder);
    }
}
```

```xml
<ImageView app:placeholder="@{@drawable/venuePlaceholder}" />
```

`Los adaptadores de vinculación que defines anulan los adaptadores predeterminados proporcionados por el marco de trabajo de Android cuando hay un conflicto.`

## Conversiones de objetos

### Conversiones personalizadas

En algunos casos, se requiere una conversión personalizada entre tipos específicos. Por ejemplo, el atributo android:background de una vista espera un Drawable, pero el valor color especificado es un número entero. El siguiente ejemplo muestra un atributo que espera un Drawable, pero se proporciona un número entero:

```xml
<View
   android:background="@{isError ? @color/red : @color/white}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"/>
    
```

Cada vez que se espera un Drawable y se muestra un número entero, se debe convertir int en ColorDrawable. La conversión se puede realizar usando un método estático con una anotación BindingConversion:
```kotlin
@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)
```

BindingConversion: anote los métodos que se utilizan para convertir automáticamente del tipo de expresión al valor utilizado en el definidor.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.