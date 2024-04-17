package estudiantes.services

import com.github.michaelbull.result.Result
import estudiantes.errors.EstudianteError
import estudiantes.models.Estudiante


interface EstudiantesService {
    fun getAll(): Result<List<Estudiante>, EstudianteError>
    fun getById(id: Long): Result<Estudiante, EstudianteError>
    fun create(estudiante: Estudiante): Result<Estudiante, EstudianteError>
    fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError>
    fun delete(id: Long): Result<Estudiante, EstudianteError>
}