import estudiantes.models.Estudiante
import estudiantes.services.PersonasService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PersonasApp : KoinComponent {
    val contPorDefecto : PersonasService by inject()


    fun run() {
        contPorDefecto.create(
            Estudiante(
                id = 8,
                nombre = "Samuel"
            )
        )

        val lista=contPorDefecto.getAll().value

        lista.forEach { println(it) }
    }
}