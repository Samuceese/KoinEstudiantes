package estudiantes.mappers

import database.EstudianteEntity
import estudiantes.dto.EstudianteDto
import estudiantes.models.Estudiante
import java.time.LocalDateTime

fun EstudianteEntity.toEstudiante(): Estudiante {
    return Estudiante(
        id = this.id,
        nombre = this.nombre,
        createAt = LocalDateTime.parse(this.created_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}

fun Estudiante.toEstudianteDto(): EstudianteDto {
    return EstudianteDto(
        id = this.id,
        nombre = this.nombre,
        createAt = this.createAt.toString(),
        isDeleted = this.isDeleted
    )
}