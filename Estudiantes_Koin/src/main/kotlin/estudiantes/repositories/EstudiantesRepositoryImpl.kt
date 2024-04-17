package estudiantes.repositories

import database.service.SqlDeLightManager
import estudiantes.mappers.toEstudiante
import estudiantes.models.Estudiante
import java.time.LocalDateTime

class EstudiantesRepositoryImpl (
    private val dbManager: SqlDeLightManager
) : EstudiantesRepository {
    private val db = dbManager.databaseQueries

    override fun findAll(): List<Estudiante> {
        println("Buscando todos los clientes")
        return db.selectAllEstudiante().executeAsList().map { it.toEstudiante() }
    }

    override fun findById(id: Long): Estudiante? {
        println("Buscando cliente por id: $id")
        return db.selectEstudianteById(id).executeAsOneOrNull()?.toEstudiante()
    }

    override fun save(estudiante: Estudiante): Estudiante {
        println("Guardando estudiante: $estudiante")

        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertEstudiante(
                nombre = estudiante.nombre,
                created_at = timeStamp
            )
        }

        return db.selectEstudianteLastInserted().executeAsOne().toEstudiante()
    }


    override fun update(id: Long, estudiante: Estudiante): Estudiante? {
        println("Actualizando cliente por id: $id")
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        result.nombre=estudiante.nombre
        result.isDeleted=estudiante.isDeleted


        db.updateEstudiante(
            nombre = result.nombre,
            is_deleted = if (result.isDeleted) 1 else 0,
            id = id,
        )
        return result
    }

    override fun delete(id: Long): Estudiante? {
        println("Borrando cliente por id: $id")
        val result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        db.updateEstudiante(
            nombre = result.nombre,
            is_deleted = 1,
            id = result.id,
        )
        result.isDeleted=true

        return result
    }

}