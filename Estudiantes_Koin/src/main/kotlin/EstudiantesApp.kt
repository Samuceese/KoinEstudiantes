import estudiantes.models.Estudiante
import estudiantes.services.EstudiantesService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EstudiantesApp : KoinComponent {
    val contPorDefecto : EstudiantesService by inject()


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