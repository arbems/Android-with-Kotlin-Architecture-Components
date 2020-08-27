# Android con Kotlin - Data Binding y ViewModel Observable

*Código de ejemplo de como implementar un componente ViewModel Observable usando Librería de Data Binding en Android con Kotlin.*

Puedes usar un componente ViewModel que implemente el [**Observable**](https://developer.android.com/reference/android/databinding/Observable) para notificar a otros componentes de la app sobre cambios en los datos, de manera similar a cómo usarías un objeto LiveData.

Hay situaciones en las que podrías preferir usar un componente ViewModel que implemente la interfaz Observable en lugar de usar objetos LiveData, incluso si pierdes las funcionalidades de administración del ciclo de vida de LiveData.

Si usas un componente ViewModel que implemente **Observable**, tendrás más control sobre los **adaptadores de vinculación** en tu app. Este patrón brinda más control sobre las notificaciones cuando cambian los datos. También permite especificar un método personalizado para establecer el valor de un atributo en la vinculación de datos bidireccional.

### Implementar un componente ViewModel observable

Crear una clase que se herede de la clase ViewModel e implemente la interfaz Observable:
```kotlin
/**
 * Un ViewModel que también es un Observable, para ser utilizado con la Librería de Data Binding.
 */
open class ObservableViewModel : ViewModel(), Observable {
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    /**
     * Notifica a los observadores que todas las propiedades de esta instancia han cambiado.
     */
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifica a los observadores que una propiedad específica ha cambiado. 
     * El getter de la propiedad que cambia debe marcarse con la anotación @Bindable 
     * para generar un campo en la clase BR que se utilizará como parámetro fieldId.
     *
     * @param fieldId El ID de BR generado para el campo enlazable.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
```

## Reference

import androidx.databinding.Observable

import androidx.databinding.PropertyChangeRegistry

import androidx.lifecycle.ViewModel

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.