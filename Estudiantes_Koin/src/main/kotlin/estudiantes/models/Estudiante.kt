package estudiantes.models

import java.time.LocalDateTime

class Estudiante(
    val id:Long,
    var nombre:String,
    val createAt: LocalDateTime=LocalDateTime.now(),
    var isDeleted:Boolean=false
){
    override fun toString(): String {
        return "Estudiante(Id:$id, Nombre: $nombre, CreateAt: $createAt)"
    }
}