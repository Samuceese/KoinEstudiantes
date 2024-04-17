package estudiantes.dto

data class EstudianteDto (
    val id:Long,
    val nombre:String,
    val createAt:String,
    val isDeleted:Boolean=false
)