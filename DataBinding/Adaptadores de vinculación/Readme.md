# Android con Kotlin - Data Binding - Adaptadores de vinculación

*Este ejemplo muestra las siguientes características de [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)*:

* Binding Adapters
* Binding Adapters múltiples parámetros
* Binding Methods
* Binding Converters

# Documentación

Los adaptadores de vinculación se encargan de realizar las llamadas de framework apropiadas para establecer valores.

La librería de Data Binding te permite especificar el método al que se llama para:
* **Establecer un valor**. Por ejemplo, lamar al método setText() o configurar un objeto de escucha de eventos, como llamar al método setOnClickListener().
* Proporcionar tu **propia lógica de vinculación**.
* Y **especificar el tipo de objeto** mostrado mediante adaptadores.

Para cada expresión de diseño, hay un adaptador de vinculación que hace las llamadas de marco de trabajo requeridas para establecer las propiedades u objetos de escucha correspondientes.

Los adaptadores de vinculación más comunes, como los de la propiedad *android:text*, están disponibles para su uso en el paquete `android.databinding.adapters`
 [**Lista de los adaptadores de vinculación comunes**](https://android.googlesource.com/platform/frameworks/data-binding/+/refs/heads/studio-master-dev/extensions/baseAdapters/src/main/java/androidx/databinding/adapters)

## Establecer valores de atributo

Cada vez que un valor vinculado cambia, la clase de vinculación generada debe invocar un método *set* en la vista con la expresión de vinculación.

Puedes permitir que la librería de Data Binding:
* seleccione automáticamente el método
* declare de manera explícita el método
* o proporcione una lógica personalizada para seleccionar un método.

### Selección automática de métodos

Para un atributo llamado *example*, la librería intenta encontrar automáticamente *setExample(arg)* que acepta tipos compatibles como el argumento.

Por ejemplo, con la expresión *android:example="@{name}"*, la librería busca un método *setExample(arg)* que acepte el tipo. Si el tipo de datos que se muestra es String, la librería busca un método *setExample()* que acepte un argumento String.

```xml
<EditText
    android:id="@+id/editTextName"
    android:inputType="textPersonName"
    android:example="@{name}" />
```

<br/>Si la expresión muestra un *Int* en su lugar, la librería busca un método *setExample()* que acepte un argumento *Int*:

```xml
<EditText
    android:id="@+id/editTextAge"
    android:inputType="number"
    android:example="@={age}" />
```

La vinculación de datos funciona incluso **si no existe un atributo** con el nombre dado. 
Puedes crear atributos para cualquier método *set* mediante vinculaciones de datos.

Por ejemplo, la clase DrawerLayout no tiene ningún atributo, pero sí muchos métodos *set*. 
En el siguiente diseño, se utilizan de forma automática los métodos *setScrimColor(int)* y *setDrawerListener(DrawerListener)* como métodos *set* para los atributos app:scrimColor y app:drawerListener, respectivamente:

```xml
<android.support.v4.widget.DrawerLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:scrimColor="@{@color/scrim}"
        app:drawerListener="@{fragment.drawerListener}" />
```

### Especifica un nombre de método personalizado (Binding Methods)

Algunos atributos tienen métodos *set* que no coinciden por nombre. En esos casos, se puede asociar un atributo con el método *set* utilizando la anotación [**BindingMethods**](https://developer.android.com/reference/android/databinding/BindingMethods):

La anotación se usa con una clase y puede contener múltiples anotaciones de [**BindingMethod**](https://developer.android.com/reference/android/databinding/BindingMethod), una para cada método al que se le cambió el nombre. 
Los métodos de vinculación son anotaciones que se pueden agregar a cualquier clase en tu app. 
En el siguiente ejemplo, el atributo *android:tint* está asociado con el método *setImageTintList(ColorStateList)*, no con *setTint()*:

```kotlin
@BindingMethods(
    value = [
        BindingMethod(
            type = android.widget.ImageView::class,
            attribute = "android:tint",
            method = "setImageTintList"
        ),
        // ...
    ]
)
```

Se utiliza dentro de una anotación **BindingMethods** para describir un cambio de nombre de un atributo al establecedor utilizado para establecer ese atributo. 
De forma predeterminada, un atributo *attr* se asociará con el setter *setAttr*.

`La mayoría de las veces no es necesario cambiar el nombre de los métodos set en las clases de marco de trabajo de Android. Los atributos ya se implementaron utilizando la convención de nombres para buscar automáticamente métodos coincidentes.`

### Proporciona lógica personalizada (Binding Adapters)

Algunos atributos requieren una lógica de vinculación personalizada. 
Por ejemplo, no hay métodos *set* asociados para el atributo *android:text* que reciba un parámetro tipo *Int*.
En su lugar, se proporciona el método *setText()* que recibe un tipo *String*.

Usando un método con la anotación [**BindingAdapter**](https://developer.android.com/reference/android/databinding/BindingAdapter) permite personalizar el comportamiento para un atributo.

```xml
<EditText
    android:id="@+id/editTextAge"
    android:inputType="number"
    android:text="@={age}" />
```

Los tipos de parámetros son importantes. El primer parámetro determina el tipo de vista asociada con el atributo. El segundo parámetro determina el tipo aceptado en la expresión de vinculación para el atributo dado.

Se llama siempre que cambia el valor del atributo:

```kotlin
@BindingAdapter("android:text")
fun setText(view: EditText, value: Int) {
    val oldValue = view.text.toString();
    if (oldValue != value.toString() && value != 0)
        view.setText(value.toString())
}
```

Para la vinculación bidireccional usamos la anotación [**InverseBindingAdapter**](https://developer.android.com/reference/android/databinding/InverseBindingAdapter).

Se llama cuando se actualiza el valor del atributo en la vista:

```kotlin
@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return if (view.text.toString() == "") 0 else view.text.toString().toInt()
}
```

También puede haber adaptadores que reciban varios atributos, por ejemplo para cargar una imagen:

```xml
<ImageView app:imageUrl="@{venue.imageUrl}" app:error="@{@drawable/venueError}" />
```

```kotlin
@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}
```

Si quieres que se llame al adaptador cuando se establece cualquiera de los atributos, puedes configurar la marca opcional **requireAll** del adaptador en *false*:

```xml
<ImageView app:placeholder="@{@drawable/venuePlaceholder}" />
```

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

Los métodos de adaptador de vinculación pueden tomar opcionalmente valores anteriores en sus controladores. 
Un método que toma valores antiguos y nuevos debe declarar todos los valores anteriores para los atributos primero, seguidos de los valores nuevos:

```kotlin
@BindingAdapter("android:text")
fun setText(view: EditText, oldValue: Int, value: Int) {
    if (oldValue != value && value != 0)
        view.setText(value.toString())
}
```

`Los adaptadores de vinculación que defines anulan los adaptadores predeterminados proporcionados por el marco de trabajo de Android cuando hay un conflicto.`

Los controladores de eventos solo se pueden usar con interfaces o clases abstractas con un método abstracto:

```xml
<View android:onLayoutChange="@{() -> handler.layoutChanged()}"/>
```

```kotlin
@BindingAdapter("android:onLayoutChange")
fun setOnLayoutChangeListener(view: View, oldValue: View.OnLayoutChangeListener?, newValue: View.OnLayoutChangeListener?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        if (oldValue != null) {
            view.removeOnLayoutChangeListener(oldValue)
        }
        if (newValue != null) {
            view.addOnLayoutChangeListener(newValue)
        }
    }
}
```

## Conversiones de objetos

### Conversión automática de objetos

...

### Conversiones personalizadas (Binding Converters)

En algunos casos, se requiere una conversión personalizada entre tipos específicos. 
Por ejemplo, el atributo *android:background* de una vista espera un *Drawable*, pero el valor color especificado es un número entero. 

El siguiente ejemplo muestra un atributo que espera un *Drawable*, pero se proporciona un número entero:

```xml
<View
   android:background="@{isError ? @color/red : @color/white}" />
    
```

Cada vez que se espera un *Drawable* y se muestra un número entero, se debe convertir *Int* en *ColorDrawable*. 
La conversión se puede realizar usando un método estático con una anotación [**BindingConversion**](https://developer.android.com/reference/android/databinding/BindingConversion):

```kotlin
@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)
```

Sin embargo, los tipos de valor proporcionados en la expresión de vinculación deben ser coherentes.
Error:

```xml
<View
   android:background="@{isError ? @drawable/error : @color/white}" />
    
```




## DataBinding Library annotations

### @Bindable

La anotación @Bindable se debe aplicar a cualquier método de acceso getter de una clase que implemente **Observable**. 
@Bindable generará durante la compilación un campo en la clase BR para identificar el campo que ha cambiado.

```kotlin
var firstName = ""

@Bindable
fun getFirstName() : String {
    return firstName
}
fun setFirstName(fName: String) {
    firstName = fName

    notifyPropertyChanged(BR.firstName)
}

// or
// Anotación @Bindable en getter
@get:Bindable
var firstName: String = ""
    set(value) {
        field = value

        notifyPropertyChanged(BR.firstName)
    }
```

Con el siguiente concepto, siempre que *name*, *firstName* o *lastName* tenga una notificación de cambio, `fullName` también se considerará y recibirá una notificación:

```kotlin
@get:Bindable("name", "firstName", "lastName")
var fullName: String = ""
    get() {
        return "${name.capitalize()} ${firstName.capitalize()} ${lastName.capitalize()}"
    }
```


### @BindingAdapter & @InverseBindingAdapter

Proporcionan lógica personalizada.

#### @BindingAdapter

La anotación **@BindingAdapter** le permite definir su propio método personalizado para cualquier atributo o atributos dados.

Los adaptadores de vinculación se utilizan para tener configuradores personalizados para alguna propiedad de sus vistas. 
El caso de uso más común en el que puedo pensar es configurar una imagen en una ImageView, donde la carga de la imagen se realiza principalmente fuera del hilo de la interfaz de usuario.

Los adaptadores de vinculación hacen que esto sea un poco más simple. Usted establece el atributo en XML y la biblioteca de Data Binding buscará el adaptador de enlace para establecer esa propiedad en su vista. 
Dado que los datos son observables, los cambios se activarán en la vista cada vez que cambien los datos.

```xml
<ImageView
    android:id="@+id/imageView"
    app:error="@{@drawable/error_image}"
    app:imageUrl="@{viewModel.imageUrl}"/>
```

```kotlin
@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}
```

#### @InverseBindingAdapter



### @BindingMethods & @BindingMethod

Especifica un nombre de método personalizado. Por defecto, un atributo está asociado con setAttribute setter.

```kotlin
@BindingMethods(value = [
    BindingMethod(
        type = android.widget.ImageView::class,
        attribute = "android:tint",
        method = "setImageTintList"
    )
])
```

### @InverseBindingMethods & @InverseBindingMethod

### @BindingConversion

### @InverseMethod

La anotación [**InverseMethod**](https://developer.android.com/reference/android/databinding/InverseMethod) se puede aplicar a cualquier método utilizado en el enlace de datos bidireccional para declarar el método utilizado para invertir la llamada cuando se pasa del valor del atributo de View al valor de los datos enlazados.
El método inverso debe tomar el mismo número de parámetros y solo el tipo de parámetro final puede diferir.
El parámetro final de este método debe coincidir con el *return value* de su inverso y el *return value* de este método debe coincidir con el parámetro final de su inverso.



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.