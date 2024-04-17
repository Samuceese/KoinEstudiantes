package estudiantes.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import estudiantes.errors.EstudianteError
import estudiantes.models.Estudiante
import estudiantes.repositories.EstudiantesRepository

class PersonasServiceImpl (
    private val estudiantesRepository: EstudiantesRepository
) : PersonasService {
    override fun getAll(): Result<List<Estudiante>, EstudianteError> {
        println("Obteniendo todos los productos")
        return Ok(estudiantesRepository.findAll())
    }


    override fun getById(id: Long): Result<Estudiante, EstudianteError> {
        println("Obteniendo producto por id: $id")
        return estudiantesRepository.findById(id)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoEncontrado("Producto no encontrado con id: $id"))
    }

    override fun create(estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        println("Guardando producto: $estudiante")
        return Ok(estudiantesRepository.save(estudiante))
    }

    override fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        println("Actualizando producto por id: $id")
        return estudiantesRepository.update(id, estudiante)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoActualizado("Producto no actualizado con id: $id"))
    }

    override fun delete(id: Long): Result<Estudiante, EstudianteError> {
        println("Borrando producto por id: $id")
        return estudiantesRepository.delete(id)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoEliminado("Producto no eliminado con id: $id"))
    }

}