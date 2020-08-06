# Android con Kotlin - ViewModel para compartir datos entre Fragments
                                                                                                  
Código de ejemplo de una aplicación simple para compartir datos entre Fragments usando ViewModel en Android con Kotlin. Temas clave cubiertos:

* Compartir datos entre Activity y Fragment con ViewModel
* Compartir datos entre Fragments con ViewModel

Es muy común que dos o más fragmentos de una actividad necesiten comunicarse entre sí. 
Los dos fragmentos deben administrar una situación en la que el otro fragmento todavía no se creó o no está visible.

Para solucionar esta dificultad habitual, puedes usar objetos ViewModel. Estos fragmentos pueden compartir un ViewModel mediante su alcance de actividad para administrar esta comunicación.

Ventajas de este enfoque:

- La Activity no necesita hacer nada ni saber sobre esta comunicación.
- Los Fragments no necesitan saber acerca del otro, excepto por el contrato de ViewModel. Si uno de los fragmentos desaparece, el otro sigue funcionando de manera habitual.
- Cada fragmento tiene su propio ciclo de vida y no se ve afectado por el ciclo de vida del otro. Si un fragmento reemplaza al otro, la IU continúa funcionando sin problemas.




## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
